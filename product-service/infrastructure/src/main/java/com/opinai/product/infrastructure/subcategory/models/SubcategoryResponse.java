package com.opinai.product.infrastructure.subcategory.models;


import com.opinai.product.domain.subcategory.Subcategory;

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
