<<<<<<<< HEAD:product-service/application/src/main/java/com/opinai/product/application/category/retrieve/CategoryOutput.java
package com.opinai.product.application.category.retrieve;


import com.opinai.product.domain.category.Category;
import com.opinai.product.domain.category.CategoryId;
========
package br.com.opinai.api.gestao.produto.application.category.retrieve;


import br.com.opinai.api.gestao.produto.domain.category.Category;
import br.com.opinai.api.gestao.produto.domain.category.CategoryId;
>>>>>>>> feat/product:api-gestao-produto/application/src/main/java/br/com/opinai/api/gestao/produto/application/category/retrieve/CategoryOutput.java

import java.time.Instant;

public record CategoryOutput(
        CategoryId id,
        String name,
<<<<<<<< HEAD:product-service/application/src/main/java/com/opinai/product/application/category/retrieve/CategoryOutput.java
        String description,
========
>>>>>>>> feat/product:api-gestao-produto/application/src/main/java/br/com/opinai/api/gestao/produto/application/category/retrieve/CategoryOutput.java
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static CategoryOutput from(final Category category) {
        return new CategoryOutput(
                category.getId(),
                category.getName(),
<<<<<<<< HEAD:product-service/application/src/main/java/com/opinai/product/application/category/retrieve/CategoryOutput.java
                category.getDescription(),
========
>>>>>>>> feat/product:api-gestao-produto/application/src/main/java/br/com/opinai/api/gestao/produto/application/category/retrieve/CategoryOutput.java
                category.isActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt()
        );
    }
}
