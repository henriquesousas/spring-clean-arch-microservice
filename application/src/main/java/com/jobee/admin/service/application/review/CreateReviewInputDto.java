package com.jobee.admin.service.application.review;

public record CreateReviewInputDto(
        String title,
        String summary,
        String userId,
        int type,
        String boughtFrom,
        int overallRating
) {

    public static CreateReviewInputDto from(
            String title,
            String summary,
            String userId,
            int type,
            String boughtFrom,
            int overallRating
    ) {
        return new CreateReviewInputDto(
                title, summary, userId, type, boughtFrom, overallRating
        );
    }
}
