package com.opinai.product.domain.category;

import java.time.Instant;

public class CategoryBuilder {
    private CategoryId categoryId;
    private final String name;
    private boolean isActive = true;
    private Instant createdAt = Instant.now();
    private Instant updateAt = Instant.now();
    private Instant deletedAt = null;

    public CategoryBuilder(String name) {
        this.name = name;
    }

    public CategoryBuilder withCategoryId(String id) {
        this.categoryId = CategoryId.from(id);
        return this;
    }

    public static CategoryBuilder newCategory(String name) {
        return new CategoryBuilder(name);
    }

    public CategoryBuilder withActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CategoryBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CategoryBuilder withUpdatedAt(Instant updatedAt) {
        this.updateAt = updatedAt;
        return this;
    }

    public CategoryBuilder withDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Category build() {
        CategoryId resolvedCategoryId = (this.categoryId == null)
                ? CategoryId.unique()
                : this.categoryId;


        return new Category(
                resolvedCategoryId,
                name,
                this.isActive,
                this.createdAt,
                this.updateAt,
                this.deletedAt
        );
    }
}
