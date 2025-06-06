package com.jobee.admin.service.application.usecases.review;

import java.util.Set;

public record CreateReviewInputDto(
        String title,
        String summary,
        String userId,
        String type,
        String boughtFrom,
        String url,
        int overallRating,
        int postSale,
        int responseTime,
        Set<String> positiveFeedback,
        Set<String> negativeFeedback
) {

    public static CreateReviewInputDto from(
            String title,
            String summary,
            String userId,
            String type,
            String boughtFrom,
            String url,
            int overallRating,
            int postSale,
            int responseTime,
            Set<String> positiveFeedback,
            Set<String> negativeFeedback
    ) {
        return new CreateReviewInputDto(
                title,
                summary,
                userId,
                type,
                boughtFrom,
                url,
                overallRating,
                postSale,
                responseTime,
                positiveFeedback,
                negativeFeedback
        );
    }
}
