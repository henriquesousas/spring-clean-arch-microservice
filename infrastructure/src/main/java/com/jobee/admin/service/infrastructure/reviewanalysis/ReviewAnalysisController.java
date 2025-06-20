package com.jobee.admin.service.infrastructure.reviewanalysis;

import com.jobee.admin.service.application.usecases.reviewanalysis.CreateReviewAnalysisInputDto;
import com.jobee.admin.service.application.usecases.reviewanalysis.CreateReviewAnalysisUseCase;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysis;
import com.jobee.admin.service.infrastructure.reviewanalysis.dtos.CreateReviewAnalysisOutputDto;
import com.jobee.admin.service.infrastructure.reviewanalysis.dtos.CreateReviewAnalysisRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ReviewAnalysisController implements ReviewAnalysisRestController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewAnalysisController.class);

    @Autowired
    private CreateReviewAnalysisUseCase createReviewAnalysisUseCase;

    @Override
    public ResponseEntity<CreateReviewAnalysisOutputDto> create(final CreateReviewAnalysisRequestDto request) {
        final var dto = CreateReviewAnalysisInputDto.from(
                request.userId(),
                request.reviewId(),
                request.type()
        );

         return createReviewAnalysisUseCase.execute(dto)
                .map(this::mapCreateReviewAnalysisOutputDto)
                .getOrElseThrow(error -> error);
    }

    public ResponseEntity<CreateReviewAnalysisOutputDto> mapCreateReviewAnalysisOutputDto(final ReviewAnalysis reviewAnalysis) {
        final var uri = URI.create("/reviewanalysis/" + reviewAnalysis.getAggregateId().getValue());
        final var output = new CreateReviewAnalysisOutputDto(reviewAnalysis.getAggregateId().getValue());
        return ResponseEntity.created(uri).body(output);
    }
}
