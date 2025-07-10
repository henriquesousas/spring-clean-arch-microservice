package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//TODO: Let it for all domains
public record ApiListResponse<T>(
        @JsonProperty("items") T items,
        @JsonProperty("total") int total
) {

    public static <T> ApiListResponse<List<T>> from(List<T> data) {
        return new ApiListResponse<>(data, data.size());
    }
}
