package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.AggregateRoot;
import com.jobee.admin.service.domain.review.events.ReviewCreatedEvent;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.*;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.InstantUtils;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Review extends AggregateRoot<ReviewId> {

    private String userId;
    private String productId;
    private Rating rating;
    private String store;
    private String title;
    private String comment;
    private Status status;
    private Url url;
    private boolean isActive;
    private final Type type;
    private final Boolean recommends;
    private final boolean verifiedPurchase;
    private final Set<Feedback> positiveFeedback;
    private final Set<Feedback> negativeFeedback;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Review(
            ReviewId reviewId,
            String userId,
            String productId,
            String title,
            String comment,
            Status status,
            Rating rating,
            Type type,
            Url url,
            String store,
            Boolean recommends,
            boolean verifiedPurchase,
            boolean isActive,
            Set<Feedback> positiveFeedback,
            Set<Feedback> negativeFeedback,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(reviewId);
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.comment = comment;
        this.status = status;
        this.rating = rating;
        this.store = store;
        this.verifiedPurchase = verifiedPurchase;
        this.url = url;
        this.recommends = recommends;
        this.type = type;
        this.isActive = isActive;
        this.positiveFeedback = new HashSet<>(positiveFeedback);
        this.negativeFeedback = new HashSet<>(negativeFeedback);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        registerEventHandler(ReviewCreatedEvent.class.getSimpleName(), event -> validate(notification));
        applyEvent(new ReviewCreatedEvent(this));
    }


    public static Review newReview(ReviewBuilder builder) {
        return new Review(
                builder.getReviewId(),
                builder.getUserId(),
                builder.getProductId(),
                builder.getTitle(),
                builder.getComment(),
                builder.getStatus(),
                builder.getRating(),
                builder.getType(),
                builder.getUrl(),
                builder.getStore(),
                builder.getRecommends(),
                builder.isVerifiedPurchase(),
                builder.isActive(),
                builder.getPositiveFeedback(),
                builder.getNegativeFeedback(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt()
        );
    }

    public void activate() {
        if (isActive()) return;

        this.isActive = true;
        this.updatedAt = InstantUtils.now();
    }

    public void deactivate() {
        if (!isActive()) return;

        this.isActive = false;
        this.updatedAt = InstantUtils.now();
        this.deletedAt = InstantUtils.now();
    }

    public void changeBoughtFrom(final String newSource) {
        if (failIfInactive("Fonte de compra não pode ser alterado com uma avaliação inativa")) return;

        this.store = newSource;
        this.updatedAt = InstantUtils.now();
    }

    public void changeSummary(final String newSummary) {
        if (failIfInactive("Os comentários não podem ser alterados para avaliações inativas")) return;

        this.comment = newSummary;
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

    public void changeRating(final Score rating) {
        if (failIfInactive("Rating não pode ser alterado em uma avaliação inativa")) return;

        this.rating = Rating.from(rating);
        this.updatedAt = InstantUtils.now();
    }

    public void addFeedback(final String feedback, final FeedbackType type) {
        if (failIfInactive("Review inativo não pode remover pontos negativos")) return;

        final var feedbacks = (type == FeedbackType.PROS)
                ? this.positiveFeedback
                : this.negativeFeedback;

        feedbacks.add(Feedback.from(feedback));
    }

    public void removeFeedback(final Feedback value, final FeedbackType feedbackType) {
        if (failIfInactive("Review inativo não pode remover pontos positivos")) return;

        Set<Feedback> feedbacks = (feedbackType == FeedbackType.PROS)
                ? this.positiveFeedback
                : this.negativeFeedback;

        final var removed = feedbacks.remove(value);
        if (!removed) {
            this.notification.append(new Error("%s não encontrado".formatted(value)));
        }
    }

    public void removeAllFeedbacks(final FeedbackType type) {
        if (failIfInactive("Review inativo não pode remover pontos positivos")) return;

        Set<Feedback> feedbacks = (type == FeedbackType.PROS)
                ? this.positiveFeedback
                : this.negativeFeedback;

        feedbacks.clear();
    }

    public void addUrl(final String url) {
        if (failIfInactive("Review inátivo não pode adicionar links.")) return;


        this.url = Url.from(url);
        if (this.url.getNotification().hasError()) {
            this.updatedAt = InstantUtils.now();
        }
    }

    private boolean failIfInactive(final String message) {
        if (!isActive()) {
            this.notification.append(new Error(message));
            return true;
        }
        return false;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ReviewValidator(this, handler).validate();
    }

    public Set<Feedback> getPositiveFeedback() {
        return Collections.unmodifiableSet(positiveFeedback);
    }

    public Set<Feedback> getNegativeFeedback() {
        return Collections.unmodifiableSet(negativeFeedback);
    }
}
