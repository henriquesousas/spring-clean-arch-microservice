package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.CollectionUtils;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude()
public record ReviewOutputPreview(
        @JsonProperty("review_id") String reviewId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("product_id") String productId,
        @JsonProperty("title") String title,
        @JsonProperty("comments") String summary,
        @JsonProperty("status") String status,
        @JsonProperty("store") String store,
        @JsonProperty("type") String type,
        @JsonProperty("recommends") Boolean recommends,
        @JsonProperty("is_verified") boolean isVerified,
        @JsonProperty("pros") Set<String> pros,
        @JsonProperty("cons") Set<String>  cons,
        @JsonProperty("ratings") RatingOutputPreview ratingResponse,
        @JsonProperty("created_at")  Instant createdAt
) {

    public static ReviewOutputPreview from(final Review review) {
        return new ReviewOutputPreview(
                review.getId().getValue(),
                review.getUserId(),
                review.getProductId(),
                review.getTitle(),
                review.getComment(),
                review.getStatus().getValue(),
                review.getStore(),
                review.getType().getValue(),
                review.getRecommends(),
                review.isVerifiedPurchase(),
                CollectionUtils.asSet(review.getPros(), Feedback::getValue),
                CollectionUtils.asSet(review.getCons(), Feedback::getValue),
                RatingOutputPreview.with(review.getRating()),
                review.getCreatedAt()
        );
    }

    public static List<ReviewOutputPreview> from(final List<Review> reviews) {
        return reviews.stream()
                .map(ReviewOutputPreview::from)
                .collect(Collectors.toList());
    }
}
