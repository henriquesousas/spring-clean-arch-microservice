package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.CollectionUtils;
import com.jobee.admin.service.domain.utils.NullableUtils;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record ReviewResponse(
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
        Instant createdAt
) {

    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(
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
                review.getCreatedAt()
        );
    }

    public static List<ReviewResponse> from(final List<Review> reviews) {
      return   reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
