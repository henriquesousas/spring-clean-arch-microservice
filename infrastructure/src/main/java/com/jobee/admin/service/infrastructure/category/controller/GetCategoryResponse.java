package com.jobee.admin.service.infrastructure.category.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.application.category.retrieve.GetCategoryOutput;
import com.jobee.admin.service.domain.pagination.Pagination;

import java.time.Instant;

//TODO: Rename to CategoryResponse
public record GetCategoryResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

    static GetCategoryResponse from(GetCategoryOutput data) {
        return new GetCategoryResponse(
                data.id().getValue(),
                data.name(),
                data.description(),
                true,
                data.createdAt(),
                data.updatedAt(),
                data.deletedAt()
        );
    }
}
