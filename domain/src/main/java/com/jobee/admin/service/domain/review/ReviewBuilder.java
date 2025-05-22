package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Rating;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class ReviewBuilder {

    private ReviewId reviewId = ReviewId.unique();
    private final String title;
    private final String comment;
    private final UserId userId;
    private Set<ReviewPoint> pros;
    private Set<ReviewPoint> cons;
    private ReviewStatus status;
    private Rating rating;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private Instant verifiedAt;

    public ReviewBuilder(String title, String comment, UserId userId) {
        this.userId =  Objects.requireNonNull(userId);
        this.title = Objects.requireNonNull(title);
        this.comment = Objects.requireNonNull(comment);
        this.pros = new HashSet<>();
        this.cons = new HashSet<>();
        this.status = ReviewStatus.PENDING;
        this.isActive = false;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public ReviewBuilder withId(ReviewId reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    public ReviewBuilder withPros(Set<ReviewPoint> pros) {
        this.pros = pros;
        return this;
    }

    public ReviewBuilder withCons(Set<ReviewPoint> cons) {
        this.cons = cons;
        return this;
    }

    public ReviewBuilder withStatus(ReviewStatus status) {
        this.status = status;
        return this;
    }

    public ReviewBuilder withRating(Rating rating) {
        this.rating = rating;
        return this;
    }

    public ReviewBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ReviewBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ReviewBuilder withDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public ReviewBuilder withVerifiedAt(Instant verifiedAt) {
        this.verifiedAt = verifiedAt;
        return this;
    }

    public ReviewBuilder withActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Review build() {
        return Review.newReview(this);
    }
}
