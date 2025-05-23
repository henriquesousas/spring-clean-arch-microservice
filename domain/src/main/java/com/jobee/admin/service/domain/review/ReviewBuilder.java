package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.LinkSite;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class ReviewBuilder {

    private ReviewId reviewId = ReviewId.unique();
    private final String title;
    private final String comment;
    private final UserId userId;
    private final ProductType type;
    private final String source;
    private Set<Feedback> positivePoints;
    private Set<Feedback> negativePoints;
    private ReviewStatus status;
    private Rating rating;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private Instant verifiedAt;
    private Boolean recommends;
    private boolean isVerified;
    private LinkSite reclameAquiUrl;

    public ReviewBuilder(
            String title,
            String comment,
            UserId userId,
            ProductType type,
            String source,
            Rating rating
    ) {
        this.userId = Objects.requireNonNull(userId);
        this.title = Objects.requireNonNull(title);
        this.comment = Objects.requireNonNull(comment);
        this.source = Objects.requireNonNull(source);
        this.rating = rating;
        this.positivePoints = Collections.unmodifiableSet(new HashSet<>());
        this.negativePoints = Collections.unmodifiableSet(new HashSet<>());
        this.status = ReviewStatus.PENDING;
        this.isActive = false;
        this.isVerified = false;
        this.type = type;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public ReviewBuilder withId(ReviewId reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    public ReviewBuilder withPositivePoints(Set<Feedback> point) {
        this.positivePoints = new HashSet<>(point);
        return this;
    }

    public ReviewBuilder withNegativePoints(Set<Feedback> point) {
        this.negativePoints = new HashSet<>(point);
        return this;
    }

    public ReviewBuilder withStatus(ReviewStatus status) {
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

    public ReviewBuilder withVerifiedAt(Instant verifiedAt) {
        this.verifiedAt = verifiedAt;
        return this;
    }

    public ReviewBuilder withActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public ReviewBuilder withIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
        return this;
    }

    public ReviewBuilder withIsRecommend(boolean recommend) {
        this.recommends = recommend;
        return this;
    }

    public ReviewBuilder withReclameAquiUrl(String url) {
        this.reclameAquiUrl = LinkSite.from(url);
        return this;
    }

    public Review build() {
        return Review.newReview(this);
    }
}
