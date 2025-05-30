package com.jobee.admin.service.infrastructure.review;


import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.valueobjects.UrlReclameAqui;
import com.jobee.admin.service.infrastructure.user.UserJpaModel;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "Review")
@Table(name = "reviews")
public class ReviewJpaModel {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", nullable = false, length = 4000)
    private String summary;

    @Column(name = "weak_points")
    private String weakPoints;

    @Column(name = "strong_points")
    private String strongPoints;

    @Column(name = "reclame_aqui")
    private String linkReclameAqui;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "review_status", nullable = false)
    private int reviewStatus;

    @Column(name = "ra_overall", nullable = false)
    private int overallRating;

    @Column(name = "ra_supporting")
    private Integer supportRating;

    @Column(name = "ra_after_sales")
    private Integer afterSalesRating;

    @Column(name = "bought_from")
    private String boughtFrom;

    @Column(name = "recommend")
    private Boolean recommend;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    @Column(name = "active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    @Column(name = "user_id", nullable = false)
    private String userId;

    public ReviewJpaModel() {
    }

    public ReviewJpaModel(
            String id,
            String userId,
            String title,
            String summary,
            String weakPoints,
            String strongPoints,
            String linkReclameAqui,
            int type,
            int reviewStatus,
            int overallRating,
            Integer supportRating,
            Integer afterSalesRating,
            String boughtFrom,
            Boolean recommend,
            boolean isVerified,
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.summary = summary;
        this.weakPoints = weakPoints;
        this.strongPoints = strongPoints;
        this.linkReclameAqui = linkReclameAqui;
        this.type = type;
        this.reviewStatus = reviewStatus;
        this.overallRating = overallRating;
        this.supportRating = supportRating;
        this.afterSalesRating = afterSalesRating;
        this.boughtFrom = boughtFrom;
        this.recommend = recommend;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ReviewJpaModel from(final Review review) {

        final var url = Optional.ofNullable(review.getUrl())
                .map(UrlReclameAqui::getValue)
                .orElse(null);

        return new ReviewJpaModel(
                review.getId().getValue(),
                review.getUserId().getValue(),
                review.getTitle(),
                review.getSummary(),
                review.getWeakPoints().asString(),
                review.getStrongPoints().asString(),
                url,
                review.getType().getValue(),
                review.getStatus().getValue(),
                review.getRatings().overallRating(),
                review.getRatings().supportRating(),
                review.getRatings().afterSalesRating(),
                review.getBoughtFrom(),
                review.getRecommends(),
                review.isVerified(),
                review.isActive(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWeakPoints() {
        return weakPoints;
    }

    public void setWeakPoints(String weakPoints) {
        this.weakPoints = weakPoints;
    }

    public String getStrongPoints() {
        return strongPoints;
    }

    public void setStrongPoints(String strongPoints) {
        this.strongPoints = strongPoints;
    }

    public String getLinkReclameAqui() {
        return linkReclameAqui;
    }

    public void setLinkReclameAqui(String linkReclameAqui) {
        this.linkReclameAqui = linkReclameAqui;
    }

    public int getProductType() {
        return type;
    }

    public void setProductType(int type) {
        this.type = type;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public int getSupportRating() {
        return supportRating;
    }

    public void setSupportRating(int supportRating) {
        this.supportRating = supportRating;
    }

    public int getAfterSalesRating() {
        return afterSalesRating;
    }

    public void setAfterSalesRating(int afterSalesRating) {
        this.afterSalesRating = afterSalesRating;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBoughtFrom() {
        return boughtFrom;
    }

    public void setBoughtFrom(String boughtFrom) {
        this.boughtFrom = boughtFrom;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
