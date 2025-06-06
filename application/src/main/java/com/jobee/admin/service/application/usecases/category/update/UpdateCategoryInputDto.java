package com.jobee.admin.service.application.usecases.category.update;

public record UpdateCategoryInputDto(
        String id,
        String name,
        String description
) {
}
