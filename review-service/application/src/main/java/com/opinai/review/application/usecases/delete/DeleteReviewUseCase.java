package com.opinai.review.application.usecases.delete;


import com.opinai.review.domain.Review;
import com.opinai.review.domain.ReviewRepository;
import com.opinai.review.domain.valueobjects.ReviewId;
import com.opinai.shared.application.Unit;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class DeleteReviewUseCase extends UseCase<String, Either<DomainException, Unit>> {

    private final ReviewRepository repository;

    public DeleteReviewUseCase(ReviewRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, Unit> execute(String id) {

        final var reviewId = ReviewId.from(id);

        if (reviewId.getNotification().hasError()) {
            final var errors = reviewId.getNotification().getErrors();
            return left(ValidationException.with(errors));
        }

        final var review = this.repository.findById(reviewId).orElse(null);

        if (review == null) {
            return left(NotFoundException.with(Review.class, reviewId));
        }

        this.repository.delete(reviewId);

        return right(new Unit());
    }
}
