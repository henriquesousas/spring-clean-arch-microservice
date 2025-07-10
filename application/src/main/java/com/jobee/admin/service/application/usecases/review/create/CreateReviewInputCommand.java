package com.jobee.admin.service.application.usecases.review.create;

import java.util.Set;

public record CreateReviewInputCommand(
        String userId,
        String productId,
        String title,
        String comment,
        String store,
        int rating,
        Boolean recommends,
        Set<String> pros,
        Set<String> cons
) {

    public static CreateReviewInputCommand from(
            String userId,
            String productId,
            String title,
            String comment,
            String store,
            int rating,
            Boolean recommends,
            Set<String> pros,
            Set<String> cons
    ) {
        return new CreateReviewInputCommand(
                userId,
                productId,
                title,
                comment,
                store,
                rating,
                recommends,
                pros,
                cons
        );
    }
}
