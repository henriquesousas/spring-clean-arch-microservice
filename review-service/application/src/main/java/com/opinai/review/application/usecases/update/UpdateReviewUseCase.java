package com.opinai.review.application.usecases.update;

import com.opinai.review.application.usecases.update.UpdateReviewDto;
import com.opinai.review.domain.Review;
import com.opinai.review.domain.ReviewRepository;
import com.opinai.review.domain.enums.FeedbackType;
import com.opinai.review.domain.valueobjects.ReviewId;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
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
        if (command.store() != null) review.changeBoughtFrom(command.store());
        if (command.summary() != null) review.changeSummary(command.summary());
        if (command.title() != null) review.changeTitle(command.title());

        if (command.overallRating() != null) {
            review.changeRating(command.overallRating());
        }

        if (!command.pros().isEmpty()) {
            review.removeAllFeedbacks(FeedbackType.PROS);
            command.pros()
                    .forEach(value -> review.addFeedback(value, FeedbackType.PROS));
        }

        if (!command.cons().isEmpty()) {
            review.removeAllFeedbacks(FeedbackType.CONS);
            command.cons()
                    .forEach(value -> review.addFeedback(value, FeedbackType.CONS));
        }

        return review;
    }
}
