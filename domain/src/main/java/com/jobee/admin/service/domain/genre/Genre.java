package com.jobee.admin.service.domain.genre;

import com.jobee.admin.service.domain.category.CategoryId;
import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.*;

@Getter
public class Genre extends AggregateRoot<GenreId> {
    private String name;
    private String description;
    private List<CategoryId> categories;
    private boolean active;
    private Instant updatedAt;
    private Instant deletedAt;
    private final Instant createdAt;

    private Genre(
            GenreId genreId,
            String name,
            String description,
            List<CategoryId> categories,
            Boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(genreId);
        this.name = name;
        this.description = description;
        this.active = active == null || active;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(updatedAt, this.createdAt);;
        this.deletedAt = deletedAt;
        this.categories = new ArrayList<>(Objects.requireNonNullElse(categories, new ArrayList<>()));
        ;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new GenreValidator(this, handler).validate();
    }

    public static Genre newGenre(GenreBuilder builder) {
        return new Genre(
                builder.getGenreId(),
                builder.getName(),
                builder.getDescription(),
                builder.getCategories(),
                builder.getActive(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt()
        );
    }

    public void update(String name, String description, List<CategoryId> categories) {
        if (!checkIfActive("Could not update a deactivated genre")) return;

        this.name = name;
        this.description = description;
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

    public void addCategories(List<CategoryId> categories) {
        if (!checkIfActive("Could not add categories to a deactivated genre")) return;

        Set<CategoryId> safeList = new HashSet<>(this.categories);
        safeList.addAll(categories);
        this.categories = new ArrayList<>(safeList);
        this.updatedAt = InstantUtils.now();
    }

    public void removeCategory(CategoryId categoryId) {
        if (!checkIfActive("Could not remove a category from a deactivated genre")) return;

        Set<CategoryId> safeList = new HashSet<>(this.categories);
        safeList.remove(categoryId);
        this.categories = new ArrayList<>(safeList);
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
