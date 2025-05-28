package com.jobee.admin.service.infrastructure.review;


import javax.persistence.*;
import java.time.Instant;

@Entity(name = "Review")
@Table(name = "reviews")
public class ReviewModel {
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

    @Column(name = "product_type", nullable = false)
    private int productType;

    @Column(name = "review_status", nullable = false)
    private int reviewStatus;

    @Column(name = "ra_overall", nullable = false)
    private int overallRating;

    @Column(name = "ra_supporting")
    private int supportRating;

    @Column(name = "ra_after_sales")
    private int afterSalesRating;

    @Column(name = "purchase_source")
    private int purchaseSource;

    @Column(name = "recommend")
    private boolean recommend;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private String  user;

    public ReviewModel() {
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
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
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

    public int getPurchaseSource() {
        return purchaseSource;
    }

    public void setPurchaseSource(int purchaseSource) {
        this.purchaseSource = purchaseSource;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
