package com.jobee.admin.service.infrastructure.review;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CreateReviewRequestDto(
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("userId") String userId,
        @JsonProperty("type") int type,
        @JsonProperty("boughtFrom") String boughtFrom,
        @JsonProperty("overallRating") int overallRating
) {



}
