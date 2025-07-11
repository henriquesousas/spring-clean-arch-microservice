package com.opinai.review.infrastructure.models;


import com.opinai.review.domain.valueobjects.Rating;

public record RatingOutputPreview(int overall) {

    public static RatingOutputPreview with(Rating rating) {
//        final var postSale = Optional.ofNullable(rating.getPostSale())
//                .map(Score::getValue)
//                .orElse(null);
//
//        final var responseTime = Optional.ofNullable(rating.getResponseTime())
//                .map(Score::getValue)
//                .orElse(null);

        return new RatingOutputPreview(rating.getValue());
    }
}
