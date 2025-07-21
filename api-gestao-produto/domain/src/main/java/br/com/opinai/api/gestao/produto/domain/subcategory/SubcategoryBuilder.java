<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
package com.opinai.product.domain.genre;

import com.opinai.product.domain.category.CategoryId;
========
package br.com.opinai.api.gestao.produto.domain.subcategory;

import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
public class GenreBuilder {
    private GenreId genreId = GenreId.unique();
    private String name;
    private final String description;
========
public class SubcategoryBuilder {
    private SubcategoryId subcategoryId = SubcategoryId.unique();
    private String name;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
    private final List<CategoryId> categories;
    private Boolean active = true;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
    public GenreBuilder(String name, String description, List<CategoryId> categories) {
        this.name = Objects.requireNonNull(name, "'Name' should not be null");
        this.description = Objects.requireNonNull(description, "'Description' should not be null");
        this.categories = requireNonNullOrEmpty(categories);
    }

    public GenreBuilder withName(String name) {
========
    public SubcategoryBuilder(String name, List<CategoryId> categories) {
        this.name = Objects.requireNonNull(name, "'Name' should not be null");
        this.categories = requireNonNullOrEmpty(categories);
    }

    public SubcategoryBuilder withName(String name) {
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
        this.name = name;
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
    public GenreBuilder withActive(boolean active) {
========
    public SubcategoryBuilder withActive(boolean active) {
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
        this.active = active;
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
    public GenreBuilder withCreatedAt(Instant createdAt) {
========
    public SubcategoryBuilder withCreatedAt(Instant createdAt) {
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
        this.createdAt = createdAt;
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
    public GenreBuilder withUpdatedAt(Instant updatedAt) {
========
    public SubcategoryBuilder withUpdatedAt(Instant updatedAt) {
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
        this.updatedAt = updatedAt;
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
    public GenreBuilder withDeletedAt(Instant deletedAt) {
========
    public SubcategoryBuilder withDeletedAt(Instant deletedAt) {
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
        this.deletedAt = deletedAt;
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/genre/GenreBuilder.java
    public GenreBuilder withId(GenreId genreId) {
        this.genreId = genreId;
        return this;
    }

    public Genre build() {
        return Genre.newGenre(this);
========
    public SubcategoryBuilder withId(SubcategoryId subcategoryId) {
        this.subcategoryId = subcategoryId;
        return this;
    }

    public Subcategory build() {
        return Subcategory.newGenre(this);
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/subcategory/SubcategoryBuilder.java
    }

    private <T> List<T> requireNonNullOrEmpty(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("'Categories' should not be null or empty");
        }
        return list;
    }

}
