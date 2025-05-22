package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Rating;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Getter
public class Review extends AggregateRoot<ReviewId> {

    private UserId userId;
    private String title;
    private String comment;
    private Set<ReviewPoint> pros;
    private Set<ReviewPoint> cons;
    private ReviewStatus status;
    private Rating rating;
    private boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private Instant verifiedAt;


    private Review(
            ReviewId reviewId,
            UserId userId,
            String title,
            String comment,
            Set<ReviewPoint> pros,
            Set<ReviewPoint> cons,
            ReviewStatus status,
            Rating rating,
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            Instant verifiedAt
    ) {
        super(reviewId);
        this.userId =userId;
        this.title = title;
        this.comment = comment;
        this.pros = pros;
        this.cons = cons;
        this.status = status;
        this.rating = rating;
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(updatedAt, InstantUtils.now());
        this.deletedAt = deletedAt;
        this.verifiedAt = verifiedAt;
        this.validate(notification);
    }

    public static Review newReview(ReviewBuilder builder) {
        return new Review(
                builder.getReviewId(),
                builder.getUserId(),
                builder.getTitle(),
                builder.getComment(),
                builder.getPros(),
                builder.getCons(),
                builder.getStatus(),
                builder.getRating(),
                builder.isActive(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt(),
                builder.getVerifiedAt()
        );
    }

    //TODO: Add implementation of bussines rules

    @Override
    public void validate(ValidationHandler handler) {

    }
}
