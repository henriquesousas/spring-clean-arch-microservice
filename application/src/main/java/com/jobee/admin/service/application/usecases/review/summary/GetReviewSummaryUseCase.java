package com.jobee.admin.service.application.usecases.review.summary;

import com.jobee.admin.service.application.ReviewApplicationService;
import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.application.usecases.review.ProductIdInputCommand;
import com.jobee.admin.service.application.usecases.review.RatingSummaryOutputCommand;
import com.jobee.admin.service.domain.exceptions.ApplicationException;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.review.ReviewRepository;
import com.jobee.admin.service.domain.review.ReviewService;
import com.jobee.admin.service.domain.review.enums.Status;
import com.jobee.admin.service.domain.review.exceptions.CanNotCalculateRatingException;
import com.jobee.admin.service.domain.review.exceptions.ReviewNotFoundException;
import io.vavr.control.Either;

public class GetReviewSummaryUseCase extends UseCase<ProductIdInputCommand, Either<DomainException, ReviewSummaryOutputCommand>> {

    private final ReviewRepository repository;
    private final ReviewService service;


    public GetReviewSummaryUseCase(final ReviewRepository repository, final ReviewService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public Either<DomainException, ReviewSummaryOutputCommand> execute(ProductIdInputCommand command) {
        final var reviewRating = this.repository.getRatings(command.productId());
        if (reviewRating == null || reviewRating.totalReviews() == 0) {
            return Either.left(new ReviewNotFoundException());
        }

        final var average = this.service.calculateAverage(reviewRating);
        final var ratingSummary = RatingSummaryOutputCommand.from(reviewRating, average);

        final var reviews = this.repository.findBy(Status.APPROVED.getValue(), null, command.productId());

        return Either.right(new ReviewSummaryOutputCommand(ratingSummary, reviews));
    }
}
