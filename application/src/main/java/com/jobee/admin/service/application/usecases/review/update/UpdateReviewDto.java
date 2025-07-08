package com.jobee.admin.service.application.usecases.review.update;

import java.util.Set;

public record UpdateReviewDto(
        String reviewId,
        String title,
        String summary,
        String type,
        String store,
        Integer overallRating,
        Set<String> pros,
        Set<String> cons) {
}
