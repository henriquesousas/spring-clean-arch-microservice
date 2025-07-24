package br.com.opinai.api.review.application.usecases.summary;

import br.com.opinai.api.review.application.usecases.ProductIdInputCommand;
import br.com.opinai.api.review.application.usecases.RatingSummaryOutputCommand;
import br.com.opinai.api.review.domain.ReviewRepository;
import br.com.opinai.api.review.domain.ReviewService;
import br.com.opinai.api.review.domain.enums.Status;
import br.com.opinai.api.review.domain.exceptions.ReviewNotFoundException;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
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
