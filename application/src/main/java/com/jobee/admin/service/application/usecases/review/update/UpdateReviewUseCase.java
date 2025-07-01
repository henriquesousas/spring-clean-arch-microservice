package com.jobee.admin.service.application.usecases.review.update;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.review.FeedbackType;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import io.vavr.control.Either;

import java.util.Objects;

public class UpdateReviewUseCase extends UseCase<UpdateReviewDto, Either<DomainException, Review>> {

    private final ReviewRepository repository;

    public UpdateReviewUseCase(ReviewRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, Review> execute(UpdateReviewDto command) {

        final var reviewId = ReviewId.from(command.reviewId());
        if (reviewId.getNotification().hasError()) {
            return Either.left(ValidationException.with(reviewId.getNotification().getErrors()));
        }

        final var review = this.repository.findById(reviewId).orElse(null);
        if (review == null) {
            return Either.left(NotFoundException.with(Review.class, reviewId));
        }

        applyChanges(command, review);

        if (review.getNotification().hasError()) {
            return Either.left(ValidationException.with(review.getNotification().getErrors()));
        }

        this.repository.update(review);
        return Either.right(review);
    }

    private Review applyChanges(UpdateReviewDto command, Review review) {
        if (command.boughtFrom() != null) review.changeBoughtFrom(command.boughtFrom());
        if (command.summary() != null) review.changeSummary(command.summary());
        if (command.title() != null) review.changeTitle(command.title());
        if (command.url() != null) review.addUrl(command.url());

        if (command.overallRating() != null) {
            review.changeRating(
                    RatingScale.of(command.overallRating()).orElse(review.getRating().getOverall()),
                    RatingScale.of(command.postSale()).orElse(null),
                    RatingScale.of(command.responseTime()).orElse(null)
            );
        }

        if (!command.positiveFeedback().isEmpty()) {
            review.removeAllFeedbacks(FeedbackType.PROS);
            command.positiveFeedback()
                    .forEach(value -> review.addFeedback(value, FeedbackType.PROS));
        }

        if (!command.negativeFeedback().isEmpty()) {
            review.removeAllFeedbacks(FeedbackType.CONS);
            command.negativeFeedback()
                    .forEach(value -> review.addFeedback(value, FeedbackType.CONS));
        }

        return review;
    }
}
