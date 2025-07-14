package com.opinai.product.application.category.create;


import com.opinai.product.domain.category.Category;

public record CreateCategoryOutputDto(
        String categoryId
) {


    public static CreateCategoryOutputDto from(String identifier) {
        return new CreateCategoryOutputDto(identifier);
    }

    public static CreateCategoryOutputDto from(Category category) {
        return new CreateCategoryOutputDto(category.getId().getValue());
    }
}
