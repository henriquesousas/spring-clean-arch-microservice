package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.usecases.review.ReviewOutputDto;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewInputDto;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.GetReviewByIdUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.GetReviewIdCommand;
import com.jobee.admin.service.infrastructure.genre.models.GenreResponse;
import com.jobee.admin.service.infrastructure.review.dtos.CreateReviewRequestDto;
import com.jobee.admin.service.infrastructure.review.models.CreateReviewResponse;
import com.jobee.admin.service.infrastructure.review.models.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@RestController
public class ReviewController implements ReviewRestController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private CreateReviewUseCase createReviewUseCase;

    @Autowired
    private GetReviewByIdUseCase getReviewByIdUseCase;

    @Override
    public ResponseEntity<CreateReviewResponse> create(CreateReviewRequestDto request) {
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
                .map(reviewId -> {
                    final var reviewIdResponse = new CreateReviewResponse(reviewId.getValue());
                    return ResponseEntity.created(URI.create("/review/" + reviewId))
                            .body(reviewIdResponse);
                })
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<ReviewResponse> getById(String id) {
        final var command = new GetReviewIdCommand(id);
        return this.getReviewByIdUseCase.execute(command)
                .map(review -> ResponseEntity.ok(ReviewResponse.from(review)))
                .getOrElseThrow(error -> error);
    }
}
