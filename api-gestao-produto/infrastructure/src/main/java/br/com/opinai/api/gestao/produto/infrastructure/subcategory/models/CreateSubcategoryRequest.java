<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/genre/models/CreateGenreRequest.java
package com.opinai.product.infrastructure.genre.models;
========
package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/subcategory/models/CreateSubcategoryRequest.java

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

<<<<<<<< HEAD:product-service/infrastructure/src/main/java/com/opinai/product/infrastructure/genre/models/CreateGenreRequest.java
public record CreateGenreRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description")String description,
========
public record CreateSubcategoryRequest(
        @JsonProperty("name") String name,
>>>>>>>> feat/product:api-gestao-produto/infrastructure/src/main/java/br/com/opinai/api/gestao/produto/infrastructure/subcategory/models/CreateSubcategoryRequest.java
        @JsonProperty("categories_id") List<String> categories
) {

}
