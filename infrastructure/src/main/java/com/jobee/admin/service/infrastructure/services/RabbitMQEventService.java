package com.jobee.admin.service.infrastructure.services;

import com.jobee.admin.service.domain.events.IntegrationEvent;
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
    public void send(IntegrationEvent<?> event) {
//        String json = Json.writeValueAsString("Hello word");
        String json = "{ \"name\": \"João\" }";
        this.operations.convertAndSend(
                this.exchange,
                this.routingKey,
                json
        );
    }
}
