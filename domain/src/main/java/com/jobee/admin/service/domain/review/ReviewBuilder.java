package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.UrlReclameAqui;
import com.jobee.admin.service.domain.review.valueobjects.rating.Ratings;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class ReviewBuilder {

    private ReviewId reviewId = ReviewId.unique();
    private final String title;
    private final String comment;
    private final UserId userId;
    private final Type type;
    private final String source;
    private Status status;
    private final Ratings rating;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private Instant verifiedAt;
    private Boolean recommends;
    private boolean isVerified;
    private UrlReclameAqui reclameAquiUrl;

    public ReviewBuilder(
            String title,
            String comment,
            UserId userId,
            Type type,
            String source,
            Ratings rating
    ) {
        this.userId = Objects.requireNonNull(userId);
        this.title = Objects.requireNonNull(title);
        this.comment = Objects.requireNonNull(comment);
        this.source = Objects.requireNonNull(source);
        this.rating = rating;
        this.status = Status.PENDING;
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

//    public ReviewBuilder withNotes(Set<String> positive, Set<String> negative) {
//        this.notes = Notes.newNote(positive, negative);
//        return this;
//    }

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
        this.reclameAquiUrl = UrlReclameAqui.from(url);
        return this;
    }

    public Review build() {
        return Review.newReview(this);
    }
}
