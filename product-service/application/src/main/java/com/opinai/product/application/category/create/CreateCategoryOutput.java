package com.opinai.product.application.category.create;


import com.opinai.product.domain.category.Category;

public record CreateCategoryOutput(
        String categoryId
) {


    public static CreateCategoryOutput from(String identifier) {
        return new CreateCategoryOutput(identifier);
    }

    public static CreateCategoryOutput from(Category category) {
        return new CreateCategoryOutput(category.getId().getValue());
    }
}
