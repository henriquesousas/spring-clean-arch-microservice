package com.jobee.admin.service.domain.analysis;

import com.jobee.admin.service.domain.AggregateRoot;
import com.jobee.admin.service.domain.analysis.events.ReviewAnalysisStartedEvent;
import com.jobee.admin.service.domain.utils.InstantUtils;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ReviewAnalysis extends AggregateRoot<ReviewAnalysisId> {
    private String moderatorId;
    private final String userId;
    private final String reviewId;
    private final TypeAnalysis type;
    private Reason reason;
    private Status status;
    private Instant startAt;
    private Instant endAt;

    private ReviewAnalysis(
            final ReviewAnalysisId reviewAnalysisId,
            final String moderatorId,
            final String userId,
            final String reviewId,
            final Reason reason,
            final TypeAnalysis type,
            final Status status,
            final Instant startAt,
            final Instant endAt
    ) {
        super(reviewAnalysisId);
        this.moderatorId = moderatorId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.reason = reason;
        this.type = type;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
        registerEventHandler(ReviewAnalysis.class.getSimpleName(), event -> validate(notification));
        applyEvent(new ReviewAnalysisStartedEvent(this));
    }

    public static ReviewAnalysis create(ReviewAnalysisBuilder builder) {
        return new ReviewAnalysis(
                builder.getReviewAnalysisId(),
                builder.getModeratorId(),
                builder.getUserId(),
                builder.getReviewId(),
                builder.getReason(),
                builder.getType(),
                builder.getStatus(),
                builder.getStartAt(),
                builder.getEndAt()
        );
    }

    public void startAnalysis(final String moderatorId) {
        if (this.status != Status.WAITING) {
            this.getNotification().append(new Error("Análise ja foi iniciada."));
            return;
        }
        this.moderatorId = moderatorId;
        this.status = Status.IN_ANALYSIS;
        this.startAt = InstantUtils.now();
    }

    public void endAnalysis(final Status newStatus, final String reason) {
        if (this.status == Status.WAITING) {
            this.getNotification().append(new Error("Análise ainda não foi iniciada."));
            return;
        }
        this.status = newStatus;
        this.reason = Reason.from(reason);
        this.endAt = InstantUtils.now();
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ReviewAnalysisValidator(this, handler).validate();
    }
}
