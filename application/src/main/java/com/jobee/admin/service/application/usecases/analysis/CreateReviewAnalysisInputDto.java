package com.jobee.admin.service.application.usecases.analysis;

public record CreateReviewAnalysisInputDto(
        String userId,
        String reviewId,
        String type
) {

    public static CreateReviewAnalysisInputDto from(String userId, String reviewId, String type) {
        return new CreateReviewAnalysisInputDto(userId, reviewId, type);
    }
}
