package com.opinai.review.application.usecases.retrieve.getbyid;

import com.opinai.review.domain.Review;
import com.opinai.review.domain.ReviewRepository;
import com.opinai.review.domain.valueobjects.ReviewId;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.exceptions.ValidationException;
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
