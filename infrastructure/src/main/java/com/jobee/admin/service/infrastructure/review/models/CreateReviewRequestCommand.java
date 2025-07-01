package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;


public record CreateReviewRequestCommand(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("user_id") String userId,
        @JsonProperty("type") String type,
        @JsonProperty("bought_from") String boughtFrom,
        @JsonProperty("url") String url,
        @JsonProperty("overall") int overallRating,
        @JsonProperty("post_sale") int postSaleRating,
        @JsonProperty("response_time") int responseTimeRating,
        @JsonProperty("positive_feedback") Set<String> positiveFeedback,
        @JsonProperty("negative_feedback") Set<String> negativeFeedback
) { }
