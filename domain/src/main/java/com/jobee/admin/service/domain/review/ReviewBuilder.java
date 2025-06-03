package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.Url;
import com.jobee.admin.service.domain.review.valueobjects.Rating;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import com.jobee.admin.service.domain.utils.InstantUtils;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
public class ReviewBuilder {

    private ReviewId reviewId = ReviewId.unique();
    private final UserId userId;
    private final String title;
    private final String summary;
    private final Rating rating;
    private final String boughtFrom;
    private boolean isActive;
    private final Type type;
    private boolean isVerified;
    private Status status;
    private Url url;
    private Boolean recommends;
    private final Set<Feedback> positiveFeedback;
    private final Set<Feedback> negativeFeedback;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public ReviewBuilder(
            String title,
            String summary,
            UserId userId,
            Type type,
            String boughtFrom,
            RatingScale overall,
            RatingScale posSale,
            RatingScale responseTime,
            Set<Feedback> positiveFeedback,
            Set<Feedback> negativeFeedback
    ) {
        this.title = Objects.requireNonNull(title);
        this.summary = Objects.requireNonNull(summary);
        this.userId = Objects.requireNonNull(userId);
        this.boughtFrom = Objects.requireNonNull(boughtFrom);
        this.rating = Rating.from(overall, posSale, responseTime);
        this.status = Status.PENDING;
        this.type = type;
        this.isActive = false;
        this.isVerified = false;
        this.positiveFeedback = Objects.requireNonNullElse(positiveFeedback, new HashSet<>());
        this.negativeFeedback = Objects.requireNonNullElse(negativeFeedback, new HashSet<>());
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
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

    public ReviewBuilder withIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
        return this;
    }

    public ReviewBuilder withIsRecommend(boolean recommend) {
        this.recommends = recommend;
        return this;
    }

    public ReviewBuilder withUrl(String url) {
        this.url = Url.from(url);
        return this;
    }

    public Review build() {
        return Review.newReview(this);
    }
}
