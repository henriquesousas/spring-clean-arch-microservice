package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//TODO: Let it for all domains
public record ApiSingleResponse<T>(
        @JsonProperty("data") T data
) {

    public static <T> ApiSingleResponse<T> from(T data) {
        return new ApiSingleResponse<>(data);
    }
}
