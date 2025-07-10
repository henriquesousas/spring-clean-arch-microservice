package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.Rating;
import com.jobee.admin.service.domain.review.valueobjects.Url;
import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.utils.InstantUtils;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
public class ReviewBuilder {

    private ReviewId reviewId = ReviewId.unique();
    private   User user;
    private final String productId;
    private final String title;
    private final String comment;
    private final Rating rating;
    private String store;
    private boolean isActive;
    private final Type type;
    private boolean verifiedPurchase;
    private Status status;
    private Boolean recommends;
    private final Set<Feedback> pros;
    private final Set<Feedback> cons;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private ReviewBuilder(
            String userId,
            String productId,
            Score rating,
            String title,
            String comment,
            Set<Feedback> pros,
            Set<Feedback> cons
    ) {
        this.user =  new User(Objects.requireNonNull(userId), null, null) ;
        this.productId = Objects.requireNonNull(productId);
        this.title = Objects.requireNonNull(title);
        this.comment = Objects.requireNonNull(comment);
        this.rating = Rating.from(rating);
        this.status = Status.PENDING;
        this.type = Type.PRODUCT;
        this.isActive = false;
        this.verifiedPurchase = false;
        this.pros = Objects.requireNonNullElse(pros, new HashSet<>());
        this.cons = Objects.requireNonNullElse(cons, new HashSet<>());
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
    }

    public static ReviewBuilder create(
            String userId,
            String productId,
            Score rating,
            String title,
            String comment,
            Set<Feedback> pros,
            Set<Feedback> cons
    ) {
        return new ReviewBuilder(userId, productId, rating, title, comment, pros, cons);
    }

    public ReviewBuilder withReviewId(String reviewId) {
        this.reviewId = ReviewId.from(reviewId);
        return this;
    }

    public ReviewBuilder withStatus(Status status) {
        this.status = status;
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

    public ReviewBuilder withActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public ReviewBuilder withVerifiedPurchase(boolean isVerified) {
        this.verifiedPurchase = isVerified;
        return this;
    }

    public ReviewBuilder withRecommend(Boolean recommend) {
        this.recommends = recommend;
        return this;
    }

    public ReviewBuilder withStore(String store) {
        this.store = store;
        return this;
    }

    public ReviewBuilder withUserName(String name) {
        this.user = new User(this.user.userId(), name, this.getUser().photoUrl());
        return this;
    }

    public ReviewBuilder withPhotoUrl(String photoUrl) {
        this.user = new User(this.user.userId(), this.getUser().name(), photoUrl);
        return this;
    }


    public Review build() {
        return Review.newReview(this);
    }
}
