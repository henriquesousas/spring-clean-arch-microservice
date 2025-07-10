package com.jobee.admin.service.application.usecases.review.average;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.application.usecases.review.RatingSummaryOutputCommand;
import com.jobee.admin.service.application.usecases.review.ProductIdInputCommand;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.ReviewService;
import com.jobee.admin.service.domain.review.exceptions.CanNotCalculateRatingException;
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
