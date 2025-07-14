package com.opinai.product.application.category.update;


import com.opinai.product.domain.category.Category;

public record UpdateCategoryOutputDto(
        String id
) {

    public static UpdateCategoryOutputDto from(Category category) {
        return new UpdateCategoryOutputDto(category.getId().getValue());
    }
}
