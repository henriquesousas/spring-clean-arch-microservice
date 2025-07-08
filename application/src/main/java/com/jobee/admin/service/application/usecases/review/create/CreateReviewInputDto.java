package com.jobee.admin.service.application.usecases.review.create;

import java.util.Set;

public record CreateReviewInputDto(
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

    public static CreateReviewInputDto from(
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
        return new CreateReviewInputDto(
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
