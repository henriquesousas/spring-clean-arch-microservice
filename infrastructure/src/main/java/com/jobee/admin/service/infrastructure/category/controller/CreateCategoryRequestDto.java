package com.jobee.admin.service.infrastructure.category.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryRequestDto(
        @JsonProperty("name") String name,
        @JsonProperty("description")String description,
        @JsonProperty("is_active") Boolean active
) {
}
