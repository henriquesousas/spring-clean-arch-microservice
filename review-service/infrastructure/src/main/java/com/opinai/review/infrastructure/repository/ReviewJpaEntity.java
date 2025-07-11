package com.opinai.review.infrastructure.repository;

import com.opinai.review.domain.Review;
import com.opinai.review.domain.ReviewBuilder;
import com.opinai.review.domain.enums.Score;
import com.opinai.review.domain.enums.Status;
import com.opinai.review.domain.valueobjects.Feedback;
import com.opinai.shared.domain.utils.CollectionUtils;
import com.opinai.shared.domain.utils.EnumUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
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
}
