package com.opinai.reviewanalyze.domain;

import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class ReviewAnalysisBuilder {

    private String moderatorId;
    private final ReviewAnalysisId reviewAnalysisId;
    private final String reviewId;
    private final String userId;
    private final TypeAnalysis type;
    private Reason reason;
    private Status status;
    private Instant startAt;
    private Instant endAt;


    public ReviewAnalysisBuilder(
            final String userId,
            final String reviewId,
            final TypeAnalysis type
    ) {
        this.userId = Objects.requireNonNull(userId);
        this.reviewId = Objects.requireNonNull(reviewId);
        this.type = Objects.requireNonNull(type);
        this.status = Status.WAITING;
        this.reviewAnalysisId = ReviewAnalysisId.unique();
    }

    public ReviewAnalysisBuilder withModeratorId(String id) {
        this.moderatorId = id;
        return this;
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
        return ReviewAnalysis.create(this);
    }
}
