package com.opinai.review.infrastructure.rabbitmq;

import com.opinai.review.infrastructure.annotations.ReviewCreatedQueue;
import com.opinai.shared.infrastructure.services.EventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Bean
    @ReviewCreatedQueue
    EventService reviewCreatedEventService(
            @ReviewCreatedQueue final QueueProperties properties,
            final RabbitOperations operations
    ) {
        return new RabbitMQEventService(properties.getExchange(), properties.getRoutingKey(), operations);
    }
}
