package com.jobee.admin.service.infrastructure.core.genre.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateGenreRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description")String description,
        @JsonProperty("categories_id") List<String> categories
) {

}
