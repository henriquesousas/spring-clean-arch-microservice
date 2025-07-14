package com.opinai.reviewanalyze.infrastructure;

import com.opinai.reviewanalyze.application.CreateReviewAnalysisInputDto;
import com.opinai.reviewanalyze.application.CreateReviewAnalysisUseCase;
import com.opinai.reviewanalyze.domain.ReviewAnalysis;
import com.opinai.reviewanalyze.infrastructure.dtos.CreateReviewAnalysisOutputDto;
import com.opinai.reviewanalyze.infrastructure.dtos.CreateReviewAnalysisRequestDto;
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
