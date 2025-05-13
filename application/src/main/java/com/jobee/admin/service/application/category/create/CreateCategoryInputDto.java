package com.jobee.admin.service.application.category.create;

public record CreateCategoryInputDto(
        String name,
        String description
) {


    public static CreateCategoryInputDto with(
            final String name,
            final String description
    ) {
        return new CreateCategoryInputDto(name, description);
    }
}
