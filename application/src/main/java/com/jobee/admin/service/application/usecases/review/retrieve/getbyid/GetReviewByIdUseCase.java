package com.jobee.admin.service.application.usecases.review.retrieve.getbyid;

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

public class GetReviewByIdUseCase extends UseCase<GetReviewIdCommand, Either<DomainException, Review>> {

    private final ReviewRepository repository;

    public GetReviewByIdUseCase(final ReviewRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, Review> execute(GetReviewIdCommand command) {

        final var reviewId = ReviewId.from(command.id());
        if(reviewId.getNotification().hasError()) {
            final var errors = reviewId.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        final var review = this.repository.findById(reviewId);

        return review
                .<Either<DomainException, Review>>map(category -> right(review.get()))
                .orElseGet(() -> left(NotFoundException.with(Review.class,reviewId)));
    }
}
