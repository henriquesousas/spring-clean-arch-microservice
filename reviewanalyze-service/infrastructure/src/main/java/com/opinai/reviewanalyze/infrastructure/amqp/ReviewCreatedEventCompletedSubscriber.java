package com.opinai.reviewanalyze.infrastructure.amqp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.opinai.reviewanalyze.application.CreateReviewAnalysisInputDto;
import com.opinai.reviewanalyze.application.CreateReviewAnalysisUseCase;
import com.opinai.reviewanalyze.infrastructure.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class ReviewCreatedEventCompletedSubscriber {

    private static final Logger log = LoggerFactory.getLogger(ReviewCreatedEventCompletedSubscriber.class);
    private static final String LISTENER_ID = "ReviewCreatedListener";

    @Autowired
    private CreateReviewAnalysisUseCase useCase;

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.review-created.queue}")
    public void onReviewCreatedMessage(@Payload String message) {
        log.info("Received message: {}", message);
        final var result = Json.readValue(
                message,
                new TypeReference<EventMessage<ReviewCreatedCompleted>>() {
                }
        );
        final var eventCommand = result.getPayload();
        final var createReviewAnalysisCommand = CreateReviewAnalysisInputDto.from(
                eventCommand.getUserId(),
                eventCommand.getAggregateId(),
                result.getPayload().getType()
        );
        this.useCase.execute(createReviewAnalysisCommand)
                .getOrElseThrow(e -> new RuntimeException(e.getMessage()));

        log.info("%s executed with success using this params %s "
                .formatted(CreateReviewAnalysisUseCase.class.getSimpleName(), message)
        );
    }
}
