package com.opinai.product.domain.subcategory;

import com.opinai.product.domain.category.CategoryId;
import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

@Getter
public class Subcategory extends AggregateRoot<SubcategoryId> {
    private String name;
    private List<CategoryId> categories;
    private boolean active;
    private Instant updatedAt;
    private Instant deletedAt;
    private final Instant createdAt;

    private Subcategory(
            SubcategoryId subcategoryId,
            String name,
            List<CategoryId> categories,
            Boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(subcategoryId);
        this.name = name;
        this.active = active == null || active;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(updatedAt, this.createdAt);
        this.deletedAt = deletedAt;
        this.categories = new ArrayList<>(Objects.requireNonNullElse(categories, new ArrayList<>()));
        validate(notification);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new SubcategoryValidator(this, handler).validate();
    }

    public static Subcategory newGenre(SubcategoryBuilder builder) {
        return new Subcategory(
                builder.getSubcategoryId(),
                builder.getName(),
                builder.getCategories(),
                builder.getActive(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt()
        );
    }

    public void update(String name,  List<CategoryId> categories) {
        if (!checkIfActive("Could not update a deactivated genre")) return;

        this.name = name;
        this.updatedAt = InstantUtils.now();
        addCategories(categories);
        validate(notification);
    }

    public void deactivate() {
        if (!isActive()) return;

        final var now = InstantUtils.now();
        this.active = false;
        this.updatedAt = now;
        this.deletedAt = now;
    }

    public void activate() {
        if (isActive()) return;

        this.active = true;
        this.updatedAt = InstantUtils.now();
        this.deletedAt = null;
    }

    public void addCategories(List<CategoryId> newCategories) {
        if (!checkIfActive("Could not add categories to a deactivated genre")) return;

        this.categories = Stream.concat(this.categories.stream(), newCategories.stream())
                .distinct()
                .toList();

        this.updatedAt = InstantUtils.now();
    }

    public void removeCategory(CategoryId categoryId) {
        if (!checkIfActive("Could not remove a category from a deactivated genre")) return;

        this.categories =  this.categories.stream()
                .filter(id -> !id.equals(categoryId))
                .distinct()
                .toList();

        this.updatedAt = InstantUtils.now();
    }

    private Boolean checkIfActive(final String errorMsg) {
        if (!this.active) {
            this.notification.append(new Error(errorMsg));
            return false;
        }
        return true;
    }
}
