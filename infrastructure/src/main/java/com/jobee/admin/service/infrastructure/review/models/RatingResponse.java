package com.jobee.admin.service.infrastructure.review.models;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.valueobjects.Rating;

import java.util.Optional;

public record RatingResponse(
        int overall,
        Integer postSale,
        Integer responseTime
) {

    public static RatingResponse with(Rating rating) {
        final var postSale = Optional.ofNullable(rating.getPostSale())
                .map(RatingScale::getValue)
                .orElse(null);

        final var responseTime = Optional.ofNullable(rating.getResponseTime())
                .map(RatingScale::getValue)
                .orElse(null);

        return new RatingResponse(rating.getOverall().getValue(), postSale, responseTime);
    }
}
