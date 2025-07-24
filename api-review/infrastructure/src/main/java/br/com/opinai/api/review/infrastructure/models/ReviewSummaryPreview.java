package br.com.opinai.api.review.infrastructure.models;

import br.com.opinai.api.review.application.usecases.RatingSummaryOutputCommand;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ReviewSummaryPreview (
        @JsonProperty("ratingSummary") RatingSummaryOutputCommand ratingSummaryOutputCommand,
        @JsonProperty("reviews") List<ReviewOutputPreview> reviewsOutputPreview
){
}
