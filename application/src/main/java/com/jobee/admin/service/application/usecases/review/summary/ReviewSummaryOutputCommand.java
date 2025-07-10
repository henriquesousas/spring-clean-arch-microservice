package com.jobee.admin.service.application.usecases.review.summary;

import com.jobee.admin.service.application.usecases.review.RatingSummaryOutputCommand;
import com.jobee.admin.service.domain.review.Review;

import java.util.List;

public record ReviewSummaryOutputCommand(
        RatingSummaryOutputCommand ratingSummary,
        List<Review> reviews
) {
}
