package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.application.usecases.review.RatingSummaryOutputCommand;

import java.util.List;

public record ReviewSummaryPreview (
        @JsonProperty("ratingSummary") RatingSummaryOutputCommand ratingSummaryOutputCommand,
        @JsonProperty("reviews") List<ReviewOutputPreview> reviewsOutputPreview
){
}
