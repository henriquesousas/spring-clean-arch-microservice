package com.opinai.review.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiSingleResponse<T>(
        @JsonProperty("data") T data
) {

    public static <T> ApiSingleResponse<T> from(T data) {
        return new ApiSingleResponse<>(data);
    }
}
