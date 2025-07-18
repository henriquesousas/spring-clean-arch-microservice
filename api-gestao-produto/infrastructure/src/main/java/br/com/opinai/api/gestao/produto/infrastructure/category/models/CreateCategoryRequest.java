package br.com.opinai.api.gestao.produto.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryRequest(
        @JsonProperty("name") String name,
        @JsonProperty("is_active") Boolean active
) {
}
