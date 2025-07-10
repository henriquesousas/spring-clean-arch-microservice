package com.jobee.admin.service.application.usecases.review.average;

import com.jobee.admin.service.domain.review.ReviewRating;

public record GetReviewAverageOutputCommand(
        long totalReviews,
        long rating1,
        long rating2,
        long rating3,
        long rating4,
        long rating5,
        double average
) {

    public static GetReviewAverageOutputCommand from(final ReviewRating reviewRating, double average) {
        return  new GetReviewAverageOutputCommand(
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
