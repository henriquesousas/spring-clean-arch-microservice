package com.jobee.admin.service.infrastructure.services;

import com.jobee.admin.service.domain.events.EventPayload;
import com.jobee.admin.service.domain.events.IntegrationEvent;
import com.jobee.admin.service.infrastructure.configuration.json.Json;
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
