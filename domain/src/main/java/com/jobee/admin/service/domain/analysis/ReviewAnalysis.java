package com.jobee.admin.service.domain.analysis;

import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ReviewAnalysis {
    private UserId userId;
    private ReviewId reviewId;
    private Reason reason;
    private TypeAnalysis type;
    private Status status;
    private Instant startAt;
    private Instant endAt;

    private ReviewAnalysis(UserId userId, ReviewId reviewId, Reason reason, TypeAnalysis type, Status status, Instant startAt, Instant endAt) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.reason = reason;
        this.type = type;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static ReviewAnalysis from(ReviewAnalysisBuilder builder) {
        return new ReviewAnalysis(
                builder.getUserId(),
                builder.getReviewId(),
                builder.getReason(),
                builder.getType(),
                builder.getStatus(),
                builder.getStartAt(),
                builder.getEndAt()
        );
    }
}
