package com.jobee.admin.service.infrastructure.core.review;

import com.jobee.admin.service.application.usecases.review.CreateReviewInputDto;
import com.jobee.admin.service.application.usecases.review.CreateReviewUseCase;
import com.jobee.admin.service.infrastructure.core.review.dtos.CreateReviewRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@RestController
public class ReviewController implements HttpReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private CreateReviewUseCase createReviewUseCase;

    @Override
    public ResponseEntity<?> create(CreateReviewRequestDto request) {
        logger.info("Start ReviewController with dto {}", request.toString());
        final var dto = CreateReviewInputDto.from(
                request.title(),
                request.summary(),
                request.userId(),
                request.type(),
                request.boughtFrom(),
                request.url(),
                request.overallRating(),
                request.postSaleRating(),
                request.responseTimeRating(),
                request.positiveFeedback(),
                request.negativeFeedback()
        );

        return createReviewUseCase.execute(dto)
                .map(reviewId -> ResponseEntity.created(URI.create("/genres/" + reviewId)).body(reviewId.getValue()))
                .getOrElseThrow(error -> error);
    }
}
