package com.opinai.review.domain;

import com.opinai.review.domain.enums.FeedbackType;
import com.opinai.review.domain.enums.Score;
import com.opinai.review.domain.enums.Status;
import com.opinai.review.domain.enums.Type;
import com.opinai.review.domain.events.ReviewCreatedEvent;
import com.opinai.review.domain.valueobjects.Feedback;
import com.opinai.review.domain.valueobjects.Rating;
import com.opinai.review.domain.valueobjects.ReviewId;
import com.opinai.shared.domain.utils.EnumUtils;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Review extends AggregateRoot<ReviewId> {

    private User user;
    private String productId;
    private Rating rating;
    private String store;
    private String title;
    private String comment;
    private Status status;
    private boolean isActive;
    private final Type type;
    private final Boolean recommends;
    private final boolean verifiedPurchase;
    private final Set<Feedback> pros;
    private final Set<Feedback> cons;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Review(
            ReviewId reviewId,
            User user,
            String productId,
            String title,
            String comment,
            Status status,
            Rating rating,
            Type type,
            String store,
            Boolean recommends,
            boolean verifiedPurchase,
            boolean isActive,
            Set<Feedback> pros,
            Set<Feedback> cons,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(reviewId);
        this.user = user;
        this.productId = productId;
        this.title = title;
        this.comment = comment;
        this.status = status;
        this.rating = rating;
        this.store = store;
        this.verifiedPurchase = verifiedPurchase;
        this.recommends = recommends;
        this.type = type;
        this.isActive = isActive;
        this.pros = new HashSet<>(pros);
        this.cons = new HashSet<>(cons);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        registerEventHandler(ReviewCreatedEvent.class.getSimpleName(), event -> validate(notification));
        applyEvent(new ReviewCreatedEvent(this));
    }


    public static Review newReview(ReviewBuilder builder) {
        return new Review(
                builder.getReviewId(),
                builder.getUser(),
                builder.getProductId(),
                builder.getTitle(),
                builder.getComment(),
                builder.getStatus(),
                builder.getRating(),
                builder.getType(),
                builder.getStore(),
                builder.getRecommends(),
                builder.isVerifiedPurchase(),
                builder.isActive(),
                builder.getPros(),
                builder.getCons(),
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

    public void changeRating(final Integer rating) {
        if (failIfInactive("Rating não pode ser alterado em uma avaliação inativa")) return;

        final var score = EnumUtils.of(Score.values(), rating);

        if (score == null) {
            this.getNotification().append(new Error("Rating inválido!"));
            return;
        }

        this.rating = Rating.from(score);
        this.updatedAt = InstantUtils.now();
    }

    public void addFeedback(final String feedback, final FeedbackType type) {
        if (failIfInactive("Review inativo não pode remover pontos negativos")) return;

        final var feedbacks = (type == FeedbackType.PROS)
                ? this.pros
                : this.cons;

        feedbacks.add(Feedback.from(feedback));
    }

    public void removeFeedback(final Feedback value, final FeedbackType feedbackType) {
        if (failIfInactive("Review inativo não pode remover pontos positivos")) return;

        Set<Feedback> feedbacks = (feedbackType == FeedbackType.PROS)
                ? this.pros
                : this.cons;

        final var removed = feedbacks.remove(value);
        if (!removed) {
            this.notification.append(new Error("%s não encontrado".formatted(value)));
        }
    }

    public void removeAllFeedbacks(final FeedbackType type) {
        if (failIfInactive("Review inativo não pode remover pontos positivos")) return;

        Set<Feedback> feedbacks = (type == FeedbackType.PROS)
                ? this.pros
                : this.cons;

        feedbacks.clear();
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

    public Set<Feedback> getPros() {
        return Collections.unmodifiableSet(pros);
    }

    public Set<Feedback> getCons() {
        return Collections.unmodifiableSet(cons);
    }
}
