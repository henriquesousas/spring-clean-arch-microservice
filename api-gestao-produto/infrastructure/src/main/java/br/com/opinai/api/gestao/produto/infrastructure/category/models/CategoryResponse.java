<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/models/CategoryResponse.java
package com.opinai.product.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opinai.product.application.category.retrieve.CategoryOutput;
========
package br.com.opinai.api.gestao.produto.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.opinai.api.gestao.produto.application.category.retrieve.CategoryOutput;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/models/CategoryResponse.java

import java.time.Instant;

public record CategoryResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/models/CategoryResponse.java
        @JsonProperty("description") String description,
========
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/models/CategoryResponse.java
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

    public static CategoryResponse from(CategoryOutput data) {
        return new CategoryResponse(
                data.id().getValue(),
                data.name(),
<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/models/CategoryResponse.java
                data.description(),
========
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/models/CategoryResponse.java
                true,
                data.createdAt(),
                data.updatedAt(),
                data.deletedAt()
        );
    }
}
