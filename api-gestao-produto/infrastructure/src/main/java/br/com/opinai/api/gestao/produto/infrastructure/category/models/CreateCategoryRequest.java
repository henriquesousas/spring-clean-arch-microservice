<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/models/CreateCategoryRequest.java
package com.opinai.product.infrastructure.category.models;
========
package br.com.opinai.api.gestao.produto.infrastructure.category.models;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/models/CreateCategoryRequest.java

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryRequest(
        @JsonProperty("name") String name,
<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/category/models/CreateCategoryRequest.java
        @JsonProperty("description")String description,
========
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/category/models/CreateCategoryRequest.java
        @JsonProperty("is_active") Boolean active
) {
}
