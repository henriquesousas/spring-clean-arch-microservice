package com.opinai.product.application.subcategory.create;

import java.util.List;

public record CreateSubcategoryInputDto(String name, List<String> categories) {

    public static CreateSubcategoryInputDto with(
            final String name,
            final List<String> categories
    ) {
        return new CreateSubcategoryInputDto(name, categories);
    }

}
