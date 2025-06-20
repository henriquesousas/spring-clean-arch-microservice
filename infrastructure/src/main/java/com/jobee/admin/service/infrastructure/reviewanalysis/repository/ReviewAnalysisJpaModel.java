package com.jobee.admin.service.infrastructure.reviewanalysis.repository;

import com.jobee.admin.service.domain.reviewanalysis.Reason;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysis;
import com.jobee.admin.service.domain.reviewanalysis.Status;
import com.jobee.admin.service.domain.reviewanalysis.TypeAnalysis;
import com.jobee.admin.service.domain.utils.EnumUtils;
import com.jobee.admin.service.domain.utils.NullableUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity(name = "ReviewAnalysis")
@Table(name = "reviews_analysis")
public class ReviewAnalysisJpaModel {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "moderator_id")
    private String moderatorId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "review_id", nullable = false)
    private String reviewId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "started_at", columnDefinition = "DATETIME(6)")
    private Instant startedAt;

    @Column(name = "finished_At", columnDefinition = "DATETIME(6)")
    private Instant finishedAt;

    public ReviewAnalysisJpaModel() {
    }

    private ReviewAnalysisJpaModel(String id, String moderatorId, String userId, String reviewId, String type, String reason, String status, Instant startedAt, Instant finishedAt) {
        this.id = id;
        this.moderatorId = moderatorId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.type = type;
        this.reason = reason;
        this.status = status;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public static ReviewAnalysisJpaModel from(final ReviewAnalysis reviewAnalysis) {
        return new ReviewAnalysisJpaModel(
                reviewAnalysis.getId().getValue(),
                reviewAnalysis.getModeratorId(),
                reviewAnalysis.getUserId(),
                reviewAnalysis.getReviewId(),
                EnumUtils.of(TypeAnalysis.values(), reviewAnalysis.getType().getValue()),
                NullableUtils.mapOrNull( reviewAnalysis.getReason(), Reason::getValue),
                EnumUtils.of(Status.values(), reviewAnalysis.getStatus().getValue()),
                reviewAnalysis.getStartAt(),
                reviewAnalysis.getEndAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }
}
