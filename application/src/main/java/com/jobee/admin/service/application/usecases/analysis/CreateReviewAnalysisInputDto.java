package com.jobee.admin.service.application.usecases.analysis;

public record CreateReviewAnalysisInputDto(
        String userId,
        String reviewId,
        String type
) {

    public static CreateReviewAnalysisInputDto with(String userId, String reviewId, String type) {
        return new CreateReviewAnalysisInputDto(userId, reviewId, type);
    }
}
