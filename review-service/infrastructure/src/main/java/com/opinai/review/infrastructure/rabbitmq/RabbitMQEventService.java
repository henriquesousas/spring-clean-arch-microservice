package com.opinai.review.infrastructure.rabbitmq;

import com.opinai.review.infrastructure.json.Json;
import com.opinai.shared.domain.events.EventPayload;
import com.opinai.shared.domain.events.IntegrationEvent;
import com.opinai.shared.infrastructure.services.EventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;

import java.util.Objects;

public class RabbitMQEventService implements EventService {

    private final String exchange;
    private final String routingKey;
    private final RabbitOperations operations;

    public RabbitMQEventService(String exchange, String routingKey, RabbitOperations operations) {
        this.exchange = Objects.requireNonNull(exchange);
        this.routingKey = Objects.requireNonNull(routingKey);
        this.operations = Objects.requireNonNull(operations);
    }

    @Override
    public void send(IntegrationEvent<? extends EventPayload> event) {
        String json = Json.writeValueAsString(event);
        this.operations.convertAndSend(
                this.exchange,
                this.routingKey,
                json
        );
    }
}
