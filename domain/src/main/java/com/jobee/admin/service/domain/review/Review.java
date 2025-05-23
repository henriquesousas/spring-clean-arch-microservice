package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.LinkSite;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
public class Review extends AggregateRoot<ReviewId> {

    private final UserId userId;
    private String title;
    private String summary;
    private Set<ReviewPoint> positivePoints;
    private Set<ReviewPoint> negativePoints;
    private ReviewStatus status;
    private Rating rating;
    private final ProductType productType;
    private LinkSite reclameAquiUrl;
    private String purchaseSource;
    private final Boolean recommends;
    private final boolean isVerified;
    private boolean isActive;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Review(
            ReviewId reviewId,
            UserId userId,
            String title,
            String summary,
            Set<ReviewPoint> pros,
            Set<ReviewPoint> cons,
            ReviewStatus status,
            Rating rating,
            ProductType productType,
            LinkSite reclameAquiUrl,
            String purchaseSource,
            Boolean recommends,
            boolean isVerified,
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(reviewId);
        this.userId = userId;
        this.title = title;
        this.summary = summary;
        this.positivePoints = pros;
        this.negativePoints = cons;
        this.status = status;
        this.rating = rating;
        this.purchaseSource = purchaseSource;
        this.isVerified = isVerified;
        this.reclameAquiUrl = reclameAquiUrl;
        this.recommends = recommends;
        this.productType = productType;
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(updatedAt, InstantUtils.now());
        this.deletedAt = deletedAt;
        validate(notification);
    }

    public static Review newReview(ReviewBuilder builder) {
        return new Review(
                builder.getReviewId(),
                builder.getUserId(),
                builder.getTitle(),
                builder.getComment(),
                builder.getPositivePoints(),
                builder.getNegativePoints(),
                builder.getStatus(),
                builder.getRating(),
                builder.getType(),
                builder.getReclameAquiUrl(),
                builder.getSource(),
                builder.getRecommends(),
                builder.isVerified(),
                builder.isActive(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt()
        );
    }

    public void activate() {
        if (isActive()) return;

        this.isActive = true;
        this.updatedAt = InstantUtils.now();
        this.deletedAt = null;
    }

    public void deactivate() {
        if (!isActive()) return;

        this.isActive = false;
        this.updatedAt = InstantUtils.now();
        this.deletedAt = InstantUtils.now();
    }

    public void updatePurchaseSource(String newSource) {
        if (failIfInactive("Fonte de compra não pode ser alterado com uma avaliação inativa")) return;

        this.purchaseSource = newSource;
        this.updatedAt = InstantUtils.now();
    }

    public void updateSummary(final String newSummary) {
        if (failIfInactive("Os comentários não podem ser alterados para avaliações inativas")) return;

        this.summary = newSummary;
        this.updatedAt = InstantUtils.now();
    }

    public void updateTitle(final String newTitle) {
        if (failIfInactive("Titúlo não pode ser alterado em uma avaliação inativa")) return;

        this.title = newTitle;
        this.updatedAt = InstantUtils.now();
    }

    public void updateReviewStatus(final ReviewStatus status) {
        if (failIfInactive("Status não pode ser alterado em uma avaliação inativa")) return;

        this.status = status;
        this.updatedAt = InstantUtils.now();
    }

    public void updateRating(
            RatingOptions overall,
            RatingOptions support,
            RatingOptions afterSales
    ) {
        if (failIfInactive("Rating não pode ser alterado em uma avaliação inativa")) return;

        this.rating = Rating.newRating(overall, support, afterSales);
        this.updatedAt = InstantUtils.now();
    }

    public void updateOverallRating(RatingOptions overall) {
        updateRating(overall, rating.getSupportResponseTimeRating(), rating.getAfterSalesServiceRating());
    }

    public void addPositivePoint(final ReviewPoint newPoint) {
        if (failIfInactive("Review inativo não pode adicionar pontos positivos")) return;

        this.positivePoints = new HashSet<>(this.positivePoints);
        this.positivePoints.add(newPoint);
        this.updatedAt = InstantUtils.now();
    }

    public void removePositivePoint(ReviewPoint point) {
        if (getPositivePoints().isEmpty()) return;

        if (failIfInactive("Review inativo não pode remover pontos positivos")) {
            return;
        }

        final var actualPoint = getPositivePoints()
                .stream()
                .filter(reviewPoint -> reviewPoint.equals(point))
                .findFirst()
                .orElse(null);

        if (actualPoint == null) {
            this.notification.append(new Error("%s não encontrado".formatted(point.getValue())));
            return;
        }

        this.positivePoints.remove(actualPoint);
        this.updatedAt = InstantUtils.now();
    }

    public void addNegativePoint(ReviewPoint newPoint) {
        if (failIfInactive("Review inativo não pode remover pontos negativos")) {
            return;
        }

        this.negativePoints = new HashSet<>(this.negativePoints);
        this.negativePoints.add(newPoint);
        this.updatedAt = InstantUtils.now();
    }

    public void removeNegativePoint(ReviewPoint point) {
        if (getNegativePoints().isEmpty()) return;

        if (failIfInactive("Review inativo não pode remover pontos negativos")) {
            return;
        }

        final var actualPoint = getNegativePoints()
                .stream()
                .filter(reviewPoint -> reviewPoint.equals(point))
                .findFirst()
                .orElse(null);

        if (actualPoint == null) {
            this.notification.append(new Error("%s não encontrado".formatted(point.getValue())));
            return;
        }

        this.negativePoints.remove(actualPoint);
        this.updatedAt = InstantUtils.now();
    }

    public void addReclameAquiUrl(String url) {
        if (failIfInactive("Review inátivo não pode adicionar links.")) return;

        this.reclameAquiUrl = LinkSite.from(url);
        this.updatedAt = InstantUtils.now();
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ReviewValidator(this, handler).validate();
    }

    private boolean failIfInactive(final String message) {
        if (!isActive()) {
            this.notification.append(new Error(message));
            return true;
        }
        return false;
    }

    public boolean isVerified() {
        return isVerified;
    }
}
