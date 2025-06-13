package com.jobee.admin.service.domain.analysis;

import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class ReviewAnalysisBuilder {

    private final UserId userId;
    private final ReviewId reviewId;
    private final TypeAnalysis type;
    private Reason reason;
    private Status status;
    private Instant startAt;
    private Instant endAt;


    public ReviewAnalysisBuilder(
            final UserId userId,
            final ReviewId reviewId,
            final TypeAnalysis type
    ) {
        this.userId = Objects.requireNonNull(userId);
        this.reviewId = Objects.requireNonNull(reviewId);
        this.type = Objects.requireNonNull(type);
        this.status = Status.WAITING;
    }

    public ReviewAnalysisBuilder withReason(String reason) {
        this.reason = Reason.from(reason);
        return this;
    }

    public ReviewAnalysisBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public ReviewAnalysisBuilder withEndAt(Instant date) {
        this.endAt = date;
        return this;
    }

    public ReviewAnalysisBuilder withStartAt(Instant date) {
        this.startAt = date;
        return this;
    }

    public ReviewAnalysis build() {
        return ReviewAnalysis.from(this);
    }
}
