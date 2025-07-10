package com.jobee.admin.service.infrastructure.review.repository;


import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewBuilder;
import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.CollectionUtils;
import com.jobee.admin.service.domain.utils.EnumUtils;

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

    @Column(name = "comment", nullable = false, length = 4000)
    private String comment;

    @Column(name = "cons")
    private String cons;

    @Column(name = "pros")
    private String pros;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "ra_overall", nullable = false)
    private int overallRating;

    @Column(name = "store")
    private String store;

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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "product_id", nullable = false)
    private String productId;

    public ReviewJpaEntity() {
    }

    public ReviewJpaEntity(
            String id,
            String userId,
            String userName,
            String photoUrl,
            String productId,
            String title,
            String comment,
            String pros,
            String cons,
            String type,
            String status,
            int overallRating,
            String store,
            Boolean recommend,
            boolean isVerified,
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.photoUrl = photoUrl;
        this.productId = productId;
        this.title = title;
        this.comment = comment;
        this.cons = cons;
        this.pros = pros;
        this.type = type;
        this.status = status;
        this.overallRating = overallRating;
        this.store = store;
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
                review.getUser().userId(),
                review.getUser().name(),
                review.getUser().photoUrl(),
                review.getProductId(),
                review.getTitle(),
                review.getComment(),
                CollectionUtils.asString(review.getPros(), it -> it.getValue().trim()),
                CollectionUtils.asString(review.getCons(), it -> it.getValue().trim()),
                review.getType().getValue(),
                review.getStatus().getValue(),
                review.getRating().getValue(),
                review.getStore(),
                review.getRecommends(),
                review.isVerifiedPurchase(),
                review.isActive(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
        );
    }

    public Review toAggregate() {
        return ReviewBuilder.create(
                        getUserId(),
                        getProductId(),
                        EnumUtils.of(Score.values(), this.getOverallRating()),
                        getTitle(),
                        getComment(),
                        CollectionUtils.asSet(getPros().split(","), Feedback::from),
                        CollectionUtils.asSet(getCons().split(","), Feedback::from)
                )
                .withReviewId(getId())
                .withUserName(getUserName())
                .withPhotoUrl(getPhotoUrl())
                .withRecommend(getRecommend())
                .withCreatedAt(getCreatedAt())
                .withStatus(EnumUtils.of(Status.values(), getStatus()))
                .withActive(isActive())
                .withVerifiedPurchase(isVerified())
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
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

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
