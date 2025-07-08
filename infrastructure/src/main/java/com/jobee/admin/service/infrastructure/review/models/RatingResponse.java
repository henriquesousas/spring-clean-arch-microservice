package com.jobee.admin.service.infrastructure.review.models;

import com.jobee.admin.service.domain.review.valueobjects.Rating;


public record RatingResponse(int overall) {

    public static RatingResponse with(Rating rating) {
//        final var postSale = Optional.ofNullable(rating.getPostSale())
//                .map(Score::getValue)
//                .orElse(null);
//
//        final var responseTime = Optional.ofNullable(rating.getResponseTime())
//                .map(Score::getValue)
//                .orElse(null);

        return new RatingResponse(rating.getValue());
    }
}
