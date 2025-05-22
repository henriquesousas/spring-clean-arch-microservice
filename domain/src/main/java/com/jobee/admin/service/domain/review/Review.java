package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Rating;
import com.jobee.admin.service.domain.review.enums.ReviewStatus;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.review.valueobjects.ReviewPoint;
import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.exceptions.ValueObjectValidationError;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import com.jobee.admin.service.domain.user.valueobjects.UserId;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Review extends AggregateRoot<ReviewId> {

    private final UserId userId;
    private String title;
    private String comment;
    private Set<ReviewPoint> positivePoints;
    private Set<ReviewPoint> negativePoints;
    private ReviewStatus status;
    private Rating rating;
    private boolean isActive;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private final Instant verifiedAt;


    private Review(
            ReviewId reviewId,
            UserId userId,
            String title,
            String comment,
            Set<ReviewPoint> pros,
            Set<ReviewPoint> cons,
            ReviewStatus status,
            Rating rating,
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            Instant verifiedAt
    ) {
        super(reviewId);
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.positivePoints = pros;
        this.negativePoints = cons;
        this.status = status;
        this.rating = rating;
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(updatedAt, InstantUtils.now());
        this.deletedAt = deletedAt;
        this.verifiedAt = verifiedAt;
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
                builder.isActive(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt(),
                builder.getVerifiedAt()
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

    public void changeComment(final String newComment) {
        if (failIfInactive("Os comentários não podem ser alterados para avaliações inativas")) return;

        this.comment = newComment;
        this.updatedAt = InstantUtils.now();
    }

    public void changeTitle(final String newTitle) {
        if (failIfInactive("Titúlo não pode ser alterado em uma avaliação inativa")) return;

        this.title = newTitle;
        this.updatedAt = InstantUtils.now();
    }

    public void changeStatus(final ReviewStatus status) {
        if (failIfInactive("Status não pode ser alterado em uma avaliação inativa")) return;

        this.status = status;
        this.updatedAt = InstantUtils.now();
    }

    public void changeRating(final Rating rating) {
        if (failIfInactive("Rating não pode ser alterado em uma avaliação inativa")) return;

        this.rating = rating;
        this.updatedAt = InstantUtils.now();
    }

    public void addPositivePoints(final ReviewPoint newPoint) {
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

    public UserId getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public Set<ReviewPoint> getPositivePoints() {
        return new HashSet<>(this.positivePoints);
    }

    public Set<ReviewPoint> getNegativePoints() {
        return new HashSet<>(this.negativePoints);
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public Rating getRating() {
        return rating;
    }

    public boolean isActive() {
        return isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

}
