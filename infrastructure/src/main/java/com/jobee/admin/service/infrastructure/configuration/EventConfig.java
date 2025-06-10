package com.jobee.admin.service.infrastructure.configuration;

import com.jobee.admin.service.infrastructure.configuration.annotations.ReviewCreatedQueue;
import com.jobee.admin.service.infrastructure.configuration.properties.amqp.QueueProperties;
import com.jobee.admin.service.infrastructure.services.EventService;
import com.jobee.admin.service.infrastructure.services.RabbitMQEventService;
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
