package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.usecases.review.create.CreateReviewInputDto;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewUseCase;
import com.jobee.admin.service.application.usecases.review.delete.DeleteReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.list.ListReviewCommand;
import com.jobee.admin.service.application.usecases.review.retrieve.list.ListReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.getbyid.GetReviewByIdUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.getbyid.GetReviewIdCommand;
import com.jobee.admin.service.infrastructure.review.models.CreateReviewRequestCommand;
import com.jobee.admin.service.infrastructure.review.models.CreateReviewResponseCommand;
import com.jobee.admin.service.infrastructure.review.models.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "reviews")
public class ReviewController implements ReviewApi {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private CreateReviewUseCase createReviewUseCase;

    @Autowired
    private GetReviewByIdUseCase getReviewByIdUseCase;

    @Autowired
    private ListReviewUseCase listReviewUseCase;

    @Autowired
    private DeleteReviewUseCase deleteReviewUseCase;

    @Override
    public ResponseEntity<CreateReviewResponseCommand> create(CreateReviewRequestCommand request) {
        logger.info("Start ReviewController with dto {}", request.toString());
        final var command = CreateReviewInputDto.from(
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

        return createReviewUseCase.execute(command)
                .map(reviewId -> {
                    final var reviewIdResponse = new CreateReviewResponseCommand(reviewId.getValue());
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

    @Override
    public ResponseEntity<List<ReviewResponse>> get(String status, String userId) {
        final var command = new ListReviewCommand(status, userId);
        return this.listReviewUseCase.execute(command)
                .map(reviews -> ResponseEntity.ok(ReviewResponse.from(reviews)))
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<Void> delete(String identifier) {
        ResponseEntity<Void> noContent = ResponseEntity.noContent().build();
        return deleteReviewUseCase.execute(identifier)
                .map(data -> noContent)
                .getOrElseThrow(error -> error);

    }
}
