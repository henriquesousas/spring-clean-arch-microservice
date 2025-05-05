package com.jobee.admin.service.application.category.retrieve;

import com.jobee.admin.service.domain.category.Category;
import com.jobee.admin.service.domain.category.CategoryId;

import java.time.Instant;

//TODO: Renomear para CategoryOutput
public record GetCategoryOutput(
        CategoryId id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static GetCategoryOutput from(final Category category) {
        return new GetCategoryOutput(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt()
        );
    }
}
