package com.jobee.admin.service.application.usecases.review.delete;

import com.jobee.admin.service.application.usecases.Unit;
import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
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
