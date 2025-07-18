package br.com.opinai.api.gestao.produto.domain.subcategory;

import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
public class SubcategoryBuilder {
    private SubcategoryId subcategoryId = SubcategoryId.unique();
    private String name;
    private final List<CategoryId> categories;
    private Boolean active = true;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public SubcategoryBuilder(String name, List<CategoryId> categories) {
        this.name = Objects.requireNonNull(name, "'Name' should not be null");
        this.categories = requireNonNullOrEmpty(categories);
    }

    public SubcategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubcategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public SubcategoryBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SubcategoryBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public SubcategoryBuilder withDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public SubcategoryBuilder withId(SubcategoryId subcategoryId) {
        this.subcategoryId = subcategoryId;
        return this;
    }

    public Subcategory build() {
        return Subcategory.newGenre(this);
    }

    private <T> List<T> requireNonNullOrEmpty(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("'Categories' should not be null or empty");
        }
        return list;
    }

}
