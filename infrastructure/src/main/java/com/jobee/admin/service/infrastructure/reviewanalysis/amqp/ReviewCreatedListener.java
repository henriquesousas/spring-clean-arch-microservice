package com.jobee.admin.service.infrastructure.reviewanalysis.amqp;

import com.jobee.admin.service.application.usecases.analysis.CreateReviewAnalysisInputDto;
import com.jobee.admin.service.application.usecases.analysis.CreateReviewAnalysisUseCase;
import com.jobee.admin.service.infrastructure.configuration.json.Json;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ReviewCreatedListener {

    private static final Logger log = LoggerFactory.getLogger(ReviewCreatedListener.class);
    private static final String LISTENER_ID = "ReviewCreatedListener";

    @Autowired
    private CreateReviewAnalysisUseCase useCase;

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.review-created.queue}")
    public void onReviewCreatedMessage(@Payload final String message, Channel channel, Message amqpMessage) throws IOException {
        log.debug("Received message: {}", message);
        final long deliveryTag = amqpMessage.getMessageProperties().getDeliveryTag();

        try {
            final var result = Json.readValue(message, ReviewCreatedMessageResult.class);

            if (result == null || result.getPayload() == null) {
                log.warn("Invalid message payload or no payload provided: {}", message);
                channel.basicNack(deliveryTag, false, false); // não requeue, envia para DLQ
                return;
            }

            final var payload = result.getPayload();
            final var dto = CreateReviewAnalysisInputDto.from(
                    payload.getUserId(),
                    payload.getAggregateId(),
                    result.getPayload().getType()
            );
            final var output = this.useCase.execute(dto);
            if (output.isLeft()) {
                log.warn("Error when execute usecase to payload: {}", message);
                channel.basicNack(deliveryTag, false, true); // requeue
                return;
            }
            channel.basicAck(deliveryTag, false);
        }catch (Exception e) {
            log.error("Error process message: {}", message, e);
            channel.basicNack(deliveryTag, false, true); // requeue
        }
    }
}
