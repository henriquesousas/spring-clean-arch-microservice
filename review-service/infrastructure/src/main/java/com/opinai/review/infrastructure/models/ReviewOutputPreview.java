package com.opinai.review.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opinai.review.domain.Review;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude()
public record ReviewOutputPreview(
        @JsonProperty("reviewId") String reviewId,
        @JsonProperty("user") UserOutputPreview user,
        @JsonProperty("productId") String productId,
        @JsonProperty("title") String title,
        @JsonProperty("comments") String summary,
        @JsonProperty("status") String status,
        @JsonProperty("store") String store,
        @JsonProperty("type") String type,
        @JsonProperty("recommends") Boolean recommends,
        @JsonProperty("verified") boolean isVerified,
        @JsonProperty("opinions") OpinionsPreview opinionsPreview,
        @JsonProperty("ratings") RatingOutputPreview ratingResponse,
        @JsonProperty("createdAt") Instant createdAt
) {

    public static ReviewOutputPreview from(final Review review) {
        return new ReviewOutputPreview(
                review.getId().getValue(),
                UserOutputPreview.from(review),
                review.getProductId(),
                review.getTitle(),
                review.getComment(),
                review.getStatus().getValue(),
                review.getStore(),
                review.getType().getValue(),
                review.getRecommends(),
                review.isVerifiedPurchase(),
                OpinionsPreview.from(review),
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
