package com.jobee.admin.service.application.usecases.reviewanalysis;

import com.jobee.admin.service.application.usecases.UseCase;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysis;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysisBuilder;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysisRepository;
import com.jobee.admin.service.domain.reviewanalysis.TypeAnalysis;
import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import io.vavr.control.Either;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class CreateReviewAnalysisUseCase extends UseCase<CreateReviewAnalysisInputDto, Either<DomainException, ReviewAnalysis>> {

    private final ReviewAnalysisRepository reviewAnalysisRepository;

    public CreateReviewAnalysisUseCase(final ReviewAnalysisRepository reviewAnalysisRepository) {
        this.reviewAnalysisRepository = reviewAnalysisRepository;
    }

    @Override
    public Either<DomainException, ReviewAnalysis> execute(CreateReviewAnalysisInputDto dto) {
        final var reviewAnalysis = new ReviewAnalysisBuilder(
                dto.userId(),
                dto.reviewId(),
                TypeAnalysis.CREATE
        ).build();

        if (reviewAnalysis.getNotification().hasError()) {
            final var errors = reviewAnalysis.getNotification().getErrors();
            return left(ValidationException.with(errors));
        }

        this.reviewAnalysisRepository.create(reviewAnalysis);
        return right(reviewAnalysis);
    }
}
