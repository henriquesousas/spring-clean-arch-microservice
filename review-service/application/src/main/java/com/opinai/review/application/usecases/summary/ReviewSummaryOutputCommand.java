package com.opinai.review.application.usecases.summary;

import com.opinai.review.application.usecases.RatingSummaryOutputCommand;
import com.opinai.review.domain.Review;

import java.util.List;

public record ReviewSummaryOutputCommand(
        RatingSummaryOutputCommand ratingSummary,
        List<Review> reviews
) {
}
