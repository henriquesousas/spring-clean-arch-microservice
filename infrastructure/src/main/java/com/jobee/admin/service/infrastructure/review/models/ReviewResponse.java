package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.Rating;
import com.jobee.admin.service.domain.utils.CollectionUtils;
import com.jobee.admin.service.domain.utils.EnumUtils;
import com.jobee.admin.service.domain.utils.NullableUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude()
public record ReviewResponse(
        String reviewId,
        String title,
        String summary,
        String status,
        String url,
        String boughtFrom,
        String type,
        Boolean recommends,
        boolean isVerified,
        String userId,
        Set<String> positiveFeedback,
        Set<String> negativeFeedback,
        Integer overall,
        Integer postSale,
        Integer responseTime,
        Instant createdAt
) {

    public static ReviewResponse from(final Review review) {

        final var postSale = Optional.ofNullable(review.getRating().getPostSale())
                .map(RatingScale::getValue)
                .orElse(null);

        final var responseTime = Optional.ofNullable(review.getRating().getResponseTime())
                .map(RatingScale::getValue)
                .orElse(null);

        return new ReviewResponse(
                review.getId().getValue(),
                review.getTitle(),
                review.getSummary(),
                review.getStatus().getValue(),
                review.getUrl().getValue(),
                review.getBoughtFrom(),
                review.getType().getValue(),
                review.getRecommends(),
                review.isVerified(),
                review.getUserId().getValue(),
                CollectionUtils.asSet(review.getPositiveFeedback(), Feedback::getValue),
                CollectionUtils.asSet(review.getNegativeFeedback(), Feedback::getValue),
                review.getRating().getOverall().getValue(),
                postSale,
                responseTime,
                review.getCreatedAt()
        );
    }

    public static List<ReviewResponse> from(final List<Review> reviews) {
        return reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
