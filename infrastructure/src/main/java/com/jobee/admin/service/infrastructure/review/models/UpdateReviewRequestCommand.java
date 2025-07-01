package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;


public record UpdateReviewRequestCommand(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("type") String type,
        @JsonProperty("bought_from") String boughtFrom,
        @JsonProperty("url") String url,
        @JsonProperty("overall_rating") int overallRating,
        @JsonProperty("post_sale") int postSale,
        @JsonProperty("response_time") int responseTime,
        @JsonProperty("positive_feedback") Set<String> positiveFeedback,
        @JsonProperty("negative_feedback") Set<String> negativeFeedback
) {
}

