<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/Category.java
package com.opinai.product.domain.category;
========
package br.com.opinai.api.gestao.produto.domain.category;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/Category.java

import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.validation.ValidationHandler;
import lombok.Getter;


import java.time.Instant;
import java.util.Objects;

@Getter
public class Category extends AggregateRoot<CategoryId> implements Cloneable {

    private String name;
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/Category.java
    private String description;
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/Category.java
    private boolean isActive;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public Category(
            CategoryId id,
            String name,
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/Category.java
            String description,
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/Category.java
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id);
        this.name = name;
<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/Category.java
        this.description = description;
========
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/Category.java
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.deletedAt = deletedAt;
    }

    public Category activate() {
        if (this.isActive) return this;

        this.isActive = true;
        this.updatedAt = Instant.now();
        this.deletedAt = null;
        return this;
    }

    public Category deactivate() {
        if (!this.isActive) return this;

        final var now = Instant.now();
        this.isActive = false;
        this.updatedAt = now;
        this.deletedAt = now;
        return this;
    }

<<<<<<<< HEAD:product-service/domain/src/main/java/com/opinai/product/domain/category/Category.java
    public Category update(String name, String description) {
        if (this.isActive) {
            this.name = name;
            this.description = description;
========
    public Category update(String name) {
        if (this.isActive) {
            this.name = name;
>>>>>>>> feat/product:api-gestao-produto/domain/src/main/java/br/com/opinai/api/gestao/produto/domain/category/Category.java
            this.updatedAt = Instant.now();
        }
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    @Override
    public Category clone() {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public CategoryId getId() {
        return this.id;
    }

}