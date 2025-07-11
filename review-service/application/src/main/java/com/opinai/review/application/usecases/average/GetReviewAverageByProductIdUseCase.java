package com.opinai.review.application.usecases.average;

import com.opinai.review.application.usecases.ProductIdInputCommand;
import com.opinai.review.application.usecases.RatingSummaryOutputCommand;
import com.opinai.review.domain.ReviewRepository;
import com.opinai.review.domain.ReviewService;
import com.opinai.review.domain.exceptions.CanNotCalculateRatingException;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import io.vavr.control.Either;

import java.util.Objects;

public class GetReviewAverageByProductIdUseCase extends UseCase<ProductIdInputCommand, Either<DomainException, RatingSummaryOutputCommand>> {

    private final ReviewRepository repository;
    private final ReviewService service;

    public GetReviewAverageByProductIdUseCase(final ReviewRepository repository, ReviewService service) {
        this.repository = Objects.requireNonNull(repository);
        this.service = service;
    }

    @Override
    public Either<DomainException, RatingSummaryOutputCommand> execute(ProductIdInputCommand command) {

        final var reviewRating = this.repository.getRatings(command.productId());
        if (reviewRating == null || reviewRating.totalReviews() == 0) {
            return Either.left(new CanNotCalculateRatingException());
        }

        final var average = this.service.calculateAverage(reviewRating);

        return Either.right(RatingSummaryOutputCommand.from(reviewRating, average));
    }
}
