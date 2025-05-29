package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.*;
import com.jobee.admin.service.domain.review.valueobjects.points.StrongPoints;
import com.jobee.admin.service.domain.review.valueobjects.points.WeakPoints;
import com.jobee.admin.service.domain.review.valueobjects.rating.OverallRating;
import com.jobee.admin.service.domain.review.valueobjects.rating.Ratings;
import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.utils.InstantUtils;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;


@Getter
public class Review extends AggregateRoot<ReviewId> {

    private String title;
    private String summary;
    private WeakPoints weakPoints;
    private StrongPoints strongPoints;
    private Status status;
    private Ratings ratings;
    private UrlReclameAqui url;
    private String boughtFrom;
    private boolean isActive;
    private final Type type;
    private final Boolean recommends;
    private final boolean isVerified;
    private final UserId userId;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Review(
            ReviewId reviewId,
            UserId userId,
            String title,
            String summary,
            Status status,
            Ratings rating,
            Type productType,
            UrlReclameAqui urlReclameAqui,
            String purchaseSource,
            Boolean recommends,
            boolean isVerified,
            boolean isActive,
            WeakPoints weakPoints,
            StrongPoints strongPoints,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(reviewId);
        this.userId = userId;
        this.title = title;
        this.summary = summary;
        this.status = status;
        this.ratings = rating;
        this.boughtFrom = purchaseSource;
        this.isVerified = isVerified;
        this.url = urlReclameAqui;
        this.recommends = recommends;
        this.type = productType;
        this.isActive = isActive;
        this.weakPoints = weakPoints;
        this.strongPoints = strongPoints;
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
                builder.getSummary(),
                builder.getStatus(),
                builder.getRatings(),
                builder.getType(),
                builder.getUrl(),
                builder.getBoughtFrom(),
                builder.getRecommends(),
                builder.isVerified(),
                builder.isActive(),
                builder.getWeakPoints(),
                builder.getStrongPoints(),
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

    public void changePurchaseSource(String newSource) {
        if (failIfInactive("Fonte de compra não pode ser alterado com uma avaliação inativa")) return;

        this.boughtFrom = newSource;
        this.updatedAt = InstantUtils.now();
    }

    public void changeSummary(final String newSummary) {
        if (failIfInactive("Os comentários não podem ser alterados para avaliações inativas")) return;

        this.summary = newSummary;
        this.updatedAt = InstantUtils.now();
    }

    public void changeTitle(final String newTitle) {
        if (failIfInactive("Titúlo não pode ser alterado em uma avaliação inativa")) return;

        this.title = newTitle;
        this.updatedAt = InstantUtils.now();
    }

    public void changeStatus(final Status status) {
        if (failIfInactive("Status não pode ser alterado em uma avaliação inativa")) return;

        this.status = status;
        this.updatedAt = InstantUtils.now();
    }

    public void changeRating(
            RatingScale overall,
            RatingScale support,
            RatingScale afterSales
    ) {
//        if (failIfInactive("Rating não pode ser alterado em uma avaliação inativa")) return;
//
//        this.rating = Ratings.newRating(overall, support, afterSales);
//        this.updatedAt = InstantUtils.now();
    }

    public void changeOverallRating(RatingScale scale) {
        this.ratings = Ratings.overall(scale);
    }

    //TODO: Refactor
    public void addPositiveNote(final String newNote) {
//        if (failIfInactive("Review inativo não pode adicionar pontos positivos")) return;
//
//        this.notes.addPositive(newNote);
//        if (!this.notes.getNotification().hasError()) {
//            this.updatedAt = InstantUtils.now();
//        }
    }

    //TODO: Refactor
    public void addNegativeNote(String newNote) {
//        if (failIfInactive("Review inativo não pode remover pontos negativos")) return;
//
//        this.notes.addNegative(newNote);
//        if (!this.notes.getNotification().hasError()) {
//            this.updatedAt = InstantUtils.now();
//        }
    }

    //TODO: Refactor
    public void removePositiveNote(final String note) {
//        if (failIfInactive("Review inativo não pode remover pontos positivos")) return;
//
//        this.notes.removePositive(note);
//        if (!this.notes.getNotification().hasError()) {
//            this.updatedAt = InstantUtils.now();
//        }
    }

    //TODO: Refactor
    public void removeNegativeNote(String note) {
//        if (failIfInactive("Review inativo não pode remover pontos negativos")) return;
//
//        this.notes.removeNegative(note);
//        if (!this.notes.getNotification().hasError()) {
//            this.updatedAt = InstantUtils.now();
//        }
    }

    //TODO: Refactor
    public void addUrlReclameAqui(String url) {
        if (failIfInactive("Review inátivo não pode adicionar links.")) return;

        this.url = UrlReclameAqui.from(url);
        if (this.url.getNotification().hasError()) {
            this.updatedAt = InstantUtils.now();
        }
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
