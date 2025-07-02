package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.domain.review.Review;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude()
public record ReviewResponse(
        @JsonProperty("review_id") String reviewId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("title") String title,
        @JsonProperty("summary") String summary,
        @JsonProperty("status") String status,
        @JsonProperty("url") String url,
        @JsonProperty("bought_from") String boughtFrom,
        @JsonProperty("type") String type,
        @JsonProperty("recommends") Boolean recommends,
        @JsonProperty("is_verified") boolean isVerified,
        @JsonProperty("feedbacks") FeedbackResponse feedbackResponse,
        @JsonProperty("ratings") RatingResponse ratingResponse,
        @JsonProperty("created_at")  Instant createdAt
) {

    public static ReviewResponse from(final Review review) {
        return new ReviewResponse(
                review.getId().getValue(),
                review.getUserId().getValue(),
                review.getTitle(),
                review.getComment(),
                review.getStatus().getValue(),
                review.getUrl().getValue(),
                review.getStore(),
                review.getType().getValue(),
                review.getRecommends(),
                review.isVerifiedPurchase(),
                FeedbackResponse.with(review.getPositiveFeedback(), review.getNegativeFeedback()),
                RatingResponse.with(review.getRating()),
                review.getCreatedAt()
        );
    }

    public static List<ReviewResponse> from(final List<Review> reviews) {
        return reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
