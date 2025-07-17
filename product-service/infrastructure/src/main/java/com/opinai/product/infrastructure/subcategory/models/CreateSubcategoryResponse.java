package com.opinai.product.infrastructure.subcategory.models;

public record CreateSubcategoryResponse(String id) {
    public static CreateSubcategoryResponse with(final String id) {
        return new CreateSubcategoryResponse(id);
    }
}
