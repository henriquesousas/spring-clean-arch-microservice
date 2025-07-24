package br.com.opinai.api.review.analise.application;

public record CreateReviewAnalysisInputDto(
        String userId,
        String reviewId,
        String type
) {

    public static CreateReviewAnalysisInputDto from(String userId, String reviewId, String type) {
        return new CreateReviewAnalysisInputDto(userId, reviewId, type);
    }
}
