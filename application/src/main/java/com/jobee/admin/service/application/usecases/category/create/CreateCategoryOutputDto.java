package com.jobee.admin.service.application.usecases.category.create;

import com.jobee.admin.service.domain.category.Category;

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
