package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateSubcategoryRequest(
        @JsonProperty("name") String name,
        @JsonProperty("categories_id") List<String> categories
) {

}
