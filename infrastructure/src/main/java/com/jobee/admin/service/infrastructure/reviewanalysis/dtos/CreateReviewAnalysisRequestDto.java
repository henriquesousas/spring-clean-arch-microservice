package com.jobee.admin.service.infrastructure.reviewanalysis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateReviewAnalysisRequestDto(
        @JsonProperty("userId") String userId,
        @JsonProperty("reviewId") String reviewId,
        @JsonProperty("type") String type
) {
}
