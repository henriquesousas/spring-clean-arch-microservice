package com.jobee.admin.service.application.usecases.review.update;

import java.util.Set;

public record UpdateReviewDto(
        String reviewId,
        String title,
        String summary,
        String type,
        String boughtFrom,
        String url,
        Integer overallRating,
        Integer postSale,
        Integer responseTime,
        Set<String> positiveFeedback,
        Set<String> negativeFeedback) {
}
