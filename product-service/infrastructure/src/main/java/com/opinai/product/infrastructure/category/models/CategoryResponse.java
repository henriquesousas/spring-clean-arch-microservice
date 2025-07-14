package com.opinai.product.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opinai.product.application.category.retrieve.CategoryOutput;

import java.time.Instant;

public record CategoryResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("is_active") Boolean active,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {

    public static CategoryResponse from(CategoryOutput data) {
        return new CategoryResponse(
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
