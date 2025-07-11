package com.opinai.review.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opinai.review.application.usecases.RatingSummaryOutputCommand;
import com.opinai.review.infrastructure.models.ReviewOutputPreview;

import java.util.List;

public record ReviewSummaryPreview (
        @JsonProperty("ratingSummary") RatingSummaryOutputCommand ratingSummaryOutputCommand,
        @JsonProperty("reviews") List<ReviewOutputPreview> reviewsOutputPreview
){
}
