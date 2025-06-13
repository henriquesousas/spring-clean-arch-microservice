package com.jobee.admin.service.infrastructure.review.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;


public record CreateReviewRequestDto(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("userId") String userId,
        @JsonProperty("type") String type,
        @JsonProperty("boughtFrom") String boughtFrom,
        @JsonProperty("url") String url,
        @JsonProperty("overallRating") int overallRating,
        @JsonProperty("postSaleRating") int postSaleRating,
        @JsonProperty("responseTimeRating") int responseTimeRating,
        @JsonProperty("positiveFeedback") Set<String> positiveFeedback,
        @JsonProperty("negativeFeedback") Set<String> negativeFeedback
) { }
