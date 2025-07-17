package com.opinai.product.application.category.create;

public record CreateCategoryInputDto(
        String name
) {


    public static CreateCategoryInputDto with(
            final String name
    ) {
        return new CreateCategoryInputDto(name);
    }
}
