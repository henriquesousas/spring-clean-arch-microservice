package br.com.opinai.api.gestao.produto.infrastructure.subcategory.models;


import br.com.opinai.api.gestao.produto.domain.subcategory.Subcategory;

import java.time.Instant;

public record SubcategoryResponse(
        String id,
        String name,
        Instant createdAt
) {

    public static SubcategoryResponse with(Subcategory subcategory) {
        return new SubcategoryResponse(subcategory.getId().getValue(), subcategory.getName(), subcategory.getCreatedAt());
    }
}
