<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryBuilder.java
package com.opinai.product.domain.category;
========
package br.com.opinai.api.gestao.produto.domain.category;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryBuilder.java

import java.time.Instant;

public class CategoryBuilder {
    private CategoryId categoryId;
    private final String name;
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryBuilder.java
    private final String description;
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryBuilder.java
    private boolean isActive = true;
    private Instant createdAt = Instant.now();
    private Instant updateAt = Instant.now();
    private Instant deletedAt = null;

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryBuilder.java
    public CategoryBuilder(String name, String description) {
        this.name = name;
        this.description = description;
========
    public CategoryBuilder(String name) {
        this.name = name;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryBuilder.java
    }

    public CategoryBuilder withCategoryId(String id) {
        this.categoryId = CategoryId.from(id);
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryBuilder.java
    public static CategoryBuilder newCategory(String name, String description) {
        return new CategoryBuilder(name, description);
========
    public static CategoryBuilder newCategory(String name) {
        return new CategoryBuilder(name);
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryBuilder.java
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
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/CategoryBuilder.java
                description,
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/CategoryBuilder.java
                this.isActive,
                this.createdAt,
                this.updateAt,
                this.deletedAt
        );
    }
}
