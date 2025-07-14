package com.opinai.reviewanalyze.application;


import com.opinai.reviewanalyze.domain.ReviewAnalysis;
import com.opinai.reviewanalyze.domain.ReviewAnalysisBuilder;
import com.opinai.reviewanalyze.domain.ReviewAnalysisRepository;
import com.opinai.reviewanalyze.domain.TypeAnalysis;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import io.vavr.control.Either;


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
            return Either.left(ValidationException.with(errors));
        }

        this.reviewAnalysisRepository.create(reviewAnalysis);
        return Either.right(reviewAnalysis);
    }
}
