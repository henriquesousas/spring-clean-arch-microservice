package com.jobee.admin.service.application;

import com.jobee.admin.service.domain.review.ReviewRating;
import com.jobee.admin.service.domain.review.ReviewService;

public class ReviewApplicationService implements ReviewService {

    @Override
    public double calculateAverage(ReviewRating reviewRating) {
        final var totalReviews = reviewRating.totalReviews();
        if (totalReviews == 0) return 0.0;

        final var totalWeightedSum =
                1 * reviewRating.rating1() +
                2 * reviewRating.rating2() +
                3 * reviewRating.rating3() +
                4 * reviewRating.rating4() +
                5 * reviewRating.rating5();

        double average = (double) totalWeightedSum / totalReviews;
        return Math.round(average * 10.0) / 10.0;
    }
}
