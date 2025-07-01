package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.AggregateRoot;
import com.jobee.admin.service.domain.review.events.ReviewCreatedEvent;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.valueobjects.*;
import com.jobee.admin.service.domain.user.valueobjects.UserId;
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

    private String title;
    private String summary;
    private Status status;
    private Rating rating;
    private Url url;
    private String boughtFrom;
    private boolean isActive;
    private final Type type;
    private final Boolean recommends;
    private final boolean isVerified;
    private final UserId userId;
    private final Set<Feedback> positiveFeedback;
    private final Set<Feedback> negativeFeedback;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Review(
            ReviewId reviewId,
            UserId userId,
            String title,
            String summary,
            Status status,
            Rating rating,
            Type type,
            Url urlRA,
            String boughtFrom,
            Boolean recommends,
            boolean isVerified,
            boolean isActive,
            Set<Feedback> positiveFeedback,
            Set<Feedback> negativeFeedback,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(reviewId);
        this.userId = userId;
        this.title = title;
        this.summary = summary;
        this.status = status;
        this.rating = rating;
        this.boughtFrom = boughtFrom;
        this.isVerified = isVerified;
        this.url = urlRA;
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
                builder.getTitle(),
                builder.getSummary(),
                builder.getStatus(),
                builder.getRating(),
                builder.getType(),
                builder.getUrl(),
                builder.getBoughtFrom(),
                builder.getRecommends(),
                builder.isVerified(),
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

    public void changeRating(final RatingScale overall, final RatingScale postSale, final RatingScale responseTime) {
        if (failIfInactive("Rating não pode ser alterado em uma avaliação inativa")) return;

        this.rating = Rating.from(overall, postSale, responseTime);
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
        return Collections.unmodifiableSet( negativeFeedback);
    }
}
