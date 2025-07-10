package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;


public record UpdateReviewRequestCommand(
        @JsonProperty("title") String title,
        @JsonProperty("comment") String summary,
        @JsonProperty("type") String type,
        @JsonProperty("store") String store,
        @JsonProperty("overallRating") int overallRating,
        @JsonProperty("pros") Set<String> pros,
        @JsonProperty("cons") Set<String> cons
) {
}

