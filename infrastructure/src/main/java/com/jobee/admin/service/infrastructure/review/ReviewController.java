package com.jobee.admin.service.infrastructure.review;

import com.jobee.admin.service.application.usecases.review.ProductIdInputCommand;
import com.jobee.admin.service.application.usecases.review.average.GetReviewAverageByProductIdUseCase;
import com.jobee.admin.service.application.usecases.review.RatingSummaryOutputCommand;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewInputCommand;
import com.jobee.admin.service.application.usecases.review.create.CreateReviewUseCase;
import com.jobee.admin.service.application.usecases.review.delete.DeleteReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.list.ListReviewCommand;
import com.jobee.admin.service.application.usecases.review.retrieve.list.ListReviewUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.getbyid.GetReviewByIdUseCase;
import com.jobee.admin.service.application.usecases.review.retrieve.getbyid.GetReviewIdCommand;
import com.jobee.admin.service.application.usecases.review.summary.GetReviewSummaryUseCase;
import com.jobee.admin.service.application.usecases.review.update.UpdateReviewDto;
import com.jobee.admin.service.application.usecases.review.update.UpdateReviewUseCase;
import com.jobee.admin.service.infrastructure.review.models.*;
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

    @Autowired
    private UpdateReviewUseCase updateReviewUseCase;

    @Autowired
    private GetReviewSummaryUseCase getReviewSummaryUseCase;

    @Autowired
    private GetReviewAverageByProductIdUseCase getReviewAverageByProductIdUseCase;

    @Override
    public ResponseEntity<CreateReviewResponseCommand> create(CreateReviewRequestCommand request) {
        logger.info("Start ReviewController with dto {}", request.toString());
        final var command = CreateReviewInputCommand.from(
                request.userId(),
                request.productId(),
                request.title(),
                request.comment(),
                request.store(),
                request.rating(),
                request.recommends(),
                request.pros(),
                request.cons()
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
    public ResponseEntity<Void> update(String identifier, UpdateReviewRequestCommand request) {

        final var command = new UpdateReviewDto(
                identifier,
                request.title(),
                request.summary(),
                request.type(),
                request.store(),
                request.overallRating(),
                request.pros(),
                request.cons()
        );
        ResponseEntity<Void> noContent = ResponseEntity.noContent().build();
        return this.updateReviewUseCase.execute(command)
                .map(data -> noContent)
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<ApiSingleResponse<ReviewOutputPreview>> getById(String id) {
        final var command = new GetReviewIdCommand(id);
        return this.getReviewByIdUseCase.execute(command)
                .map(review -> {
                    final var preview = ReviewOutputPreview.from(review);
                    return ResponseEntity.ok(ApiSingleResponse.from(preview));
                })
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<ApiSingleResponse<RatingSummaryOutputCommand>> getReviewAverage(String productId) {
        //TODO: Change JSON response the property total_reviews to totalReviews
        final var command = new ProductIdInputCommand(productId);
        return this.getReviewAverageByProductIdUseCase.execute(command)
                .map(ratingSummaryOutputCommand -> {
                    return ResponseEntity.ok(ApiSingleResponse.from(ratingSummaryOutputCommand));
                })
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<ApiSingleResponse<ReviewSummaryPreview>> getReviewSummary(String productId) {
        final var command = new ProductIdInputCommand(productId);
        return this.getReviewSummaryUseCase.execute(command)
                .map(reviewSummary -> {
                    final var ratingSummary = reviewSummary.ratingSummary();
                    final var reviews = ReviewOutputPreview.from(reviewSummary.reviews());
                    final var output = new ReviewSummaryPreview(ratingSummary, reviews);
                    return ResponseEntity.ok(ApiSingleResponse.from(output));
                })
                .getOrElseThrow(error -> error);
    }

    @Override
    public ResponseEntity<ApiListResponse<List<ReviewOutputPreview>>> get(String status, String userId, String productId) {
        final var command = new ListReviewCommand(status, userId, productId);
        return this.listReviewUseCase.execute(command)
                .map(reviews -> {
                    final var preview = ReviewOutputPreview.from(reviews);
                    return ResponseEntity.ok(ApiListResponse.from(preview));
                })
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
