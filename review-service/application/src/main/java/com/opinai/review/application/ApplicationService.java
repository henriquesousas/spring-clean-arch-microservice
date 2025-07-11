package com.opinai.review.application;

import com.opinai.review.domain.ReviewRating;
import com.opinai.review.domain.ReviewService;

public class ApplicationService implements ReviewService {

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
