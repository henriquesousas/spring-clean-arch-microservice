package com.opinai.review.application.usecases;


import com.opinai.review.domain.ReviewRating;

public record RatingSummaryOutputCommand(
        long totalReviews,
        long rating1,
        long rating2,
        long rating3,
        long rating4,
        long rating5,
        double average
) {

    public static RatingSummaryOutputCommand from(final ReviewRating reviewRating, double average) {
        return  new RatingSummaryOutputCommand(
                reviewRating.totalReviews(),
                reviewRating.rating1(),
                reviewRating.rating2(),
                reviewRating.rating3(),
                reviewRating.rating4(),
                reviewRating.rating5(),
                average
        );
    }
}
