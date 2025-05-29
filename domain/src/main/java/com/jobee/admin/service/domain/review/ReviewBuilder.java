package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.UrlReclameAqui;
import com.jobee.admin.service.domain.review.valueobjects.points.StrongPoints;
import com.jobee.admin.service.domain.review.valueobjects.points.WeakPoints;
import com.jobee.admin.service.domain.review.valueobjects.rating.Ratings;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Getter
public class ReviewBuilder {

    private ReviewId reviewId = ReviewId.unique();
    private final UserId userId;
    private final String title;
    private final String summary;
    private final Ratings ratings;
    private final String boughtFrom;
    private boolean isActive;
    private final Type type;
    private boolean isVerified;
    private Status status;
    private UrlReclameAqui url;
    private Boolean recommends;
    private WeakPoints weakPoints;
    private StrongPoints strongPoints;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public ReviewBuilder(
            String title,
            String summary,
            UserId userId,
            Type type,
            String boughtFrom,
            RatingScale overallRating
    ) {
        this.title = Objects.requireNonNull(title);
        this.summary = Objects.requireNonNull(summary);
        this.userId = Objects.requireNonNull(userId);
        this.ratings =  Ratings.overall(overallRating);
        this.status = Status.PENDING;
        this.boughtFrom = Objects.requireNonNull(boughtFrom);
        this.type = type;
        this.isActive = false;
        this.isVerified = false;
        this.weakPoints = WeakPoints.from();
        this.strongPoints = StrongPoints.from();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public ReviewBuilder withReviewId(String reviewId) {
        this.reviewId = ReviewId.from(reviewId);
        return this;
    }

    public ReviewBuilder withWeakPoints(Set<String> weakPoints) {
        this.weakPoints = WeakPoints.from(weakPoints);
        return this;
    }

    public ReviewBuilder withStrongPoints(Set<String> strongPoints) {
        this.strongPoints = StrongPoints.from(strongPoints);
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

    public ReviewBuilder withIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
        return this;
    }

    public ReviewBuilder withIsRecommend(boolean recommend) {
        this.recommends = recommend;
        return this;
    }

    public ReviewBuilder withReclameAquiUrl(String url) {
        this.url = UrlReclameAqui.from(url);
        return this;
    }

    public Review build() {
        return Review.newReview(this);
    }
}
