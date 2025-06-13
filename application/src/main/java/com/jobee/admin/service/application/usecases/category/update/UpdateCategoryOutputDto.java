package com.jobee.admin.service.application.usecases.category.update;

import com.jobee.admin.service.domain.category.Category;

public record UpdateCategoryOutputDto(
        String id
) {

    public static UpdateCategoryOutputDto from(Category category) {
        return new UpdateCategoryOutputDto(category.getId().getValue());
    }
}
