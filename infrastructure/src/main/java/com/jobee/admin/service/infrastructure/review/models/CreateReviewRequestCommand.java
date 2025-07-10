package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record CreateReviewRequestCommand(
        @JsonProperty("userId") String userId,
        @JsonProperty("productId") String productId,
        @JsonProperty("title") String title,
        @JsonProperty("comment") String comment,
        @JsonProperty("store") String store,
        @JsonProperty("rating") int rating,
        @JsonProperty("recommends") boolean recommends,
        @JsonProperty("pros") Set<String> pros,
        @JsonProperty("cons") Set<String> cons
) { }
