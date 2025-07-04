package com.jobee.admin.service.infrastructure.review.repository;


import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewBuilder;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.review.valueobjects.Url;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import com.jobee.admin.service.domain.utils.CollectionUtils;
import com.jobee.admin.service.domain.utils.EnumUtils;
import com.jobee.admin.service.domain.utils.NullableUtils;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "Review")
@Table(name = "reviews")
public class ReviewJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", nullable = false, length = 4000)
    private String summary;

    @Column(name = "negative_feedback")
    private String negativeFeedback;

    @Column(name = "positive_feedback")
    private String positiveFeedback;

    @Column(name = "reclame_aqui")
    private String urlReclameAqui;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "ra_overall", nullable = false)
    private int overallRating;

    @Column(name = "ra_response_time")
    private Integer responseTime;

    @Column(name = "ra_post_sale")
    private Integer postSale;

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

    public ReviewJpaEntity() {
    }

    public ReviewJpaEntity(
            String id,
            String userId,
            String title,
            String summary,
            String positiveFeedback,
            String negativeFeedback,
            String url,
            String type,
            String status,
            int overallRating,
            Integer responseTime,
            Integer postSale,
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
        this.negativeFeedback = negativeFeedback;
        this.positiveFeedback = positiveFeedback;
        this.urlReclameAqui = url;
        this.type = type;
        this.status = status;
        this.overallRating = overallRating;
        this.responseTime = responseTime;
        this.postSale = postSale;
        this.boughtFrom = boughtFrom;
        this.recommend = recommend;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ReviewJpaEntity from(final Review review) {

        return new ReviewJpaEntity(
                review.getId().getValue(),
                review.getUserId().getValue(),
                review.getTitle(),
                review.getSummary(),
                CollectionUtils.asString(review.getPositiveFeedback(), it -> it.getValue().trim()),
                CollectionUtils.asString(review.getNegativeFeedback(), it -> it.getValue().trim()),
                NullableUtils.mapOrNull(review.getUrl(), Url::getValue),
                review.getType().getValue(),
                review.getStatus().getValue(),
                review.getRating().getOverall().getValue(),
                NullableUtils.mapOrNull(review.getRating().getResponseTime(), RatingScale::getValue),
                NullableUtils.mapOrNull(review.getRating().getPostSale(), RatingScale::getValue),
                review.getBoughtFrom(),
                review.getRecommends(),
                review.isVerified(),
                review.isActive(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
        );
    }

    public Review toAggregate() {
        return new ReviewBuilder(
                getTitle(),
                getSummary(),
                UserId.from(getUserId()),
                EnumUtils.of(Type.values(), getType()),
                getBoughtFrom(),
                Url.from(getUrlReclameAqui()),
                EnumUtils.of(RatingScale.values(), this.getOverallRating()),
                EnumUtils.of(RatingScale.values(), this.getPostSale()),
                EnumUtils.of(RatingScale.values(), this.getResponseTime()),
                CollectionUtils.asSet(getPositiveFeedback().split(","), Feedback::from),
                CollectionUtils.asSet(getNegativeFeedback().split(","), Feedback::from)
        )
                .withReviewId(getId())
                .withUrl(getUrlReclameAqui())
                .withIsRecommend(isRecommend())
                .withCreatedAt(getCreatedAt())
                .withStatus(EnumUtils.of(Status.values(), getStatus()))
                .withActive(isActive())
                .withIsVerified(isVerified())
                .withUpdatedAt(getUpdatedAt())
                .withDeletedAt(getDeletedAt())
                .build();
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

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getPostSale() {
        return postSale;
    }

    public void setPostSale(int postSale) {
        this.postSale = postSale;
    }

    public String getBoughtFrom() {
        return boughtFrom;
    }

    public void setBoughtFrom(String boughtFrom) {
        this.boughtFrom = boughtFrom;
    }

    public Boolean isRecommend() {
        return recommend;
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

    public String getNegativeFeedback() {
        return negativeFeedback;
    }

    public void setNegativeFeedback(String negativeFeedback) {
        this.negativeFeedback = negativeFeedback;
    }

    public String getPositiveFeedback() {
        return positiveFeedback;
    }

    public void setPositiveFeedback(String positiveFeedback) {
        this.positiveFeedback = positiveFeedback;
    }

    public String getUrlReclameAqui() {
        return urlReclameAqui;
    }

    public void setUrlReclameAqui(String urlReclameAqui) {
        this.urlReclameAqui = urlReclameAqui;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public void setPostSale(Integer postSale) {
        this.postSale = postSale;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }
}
