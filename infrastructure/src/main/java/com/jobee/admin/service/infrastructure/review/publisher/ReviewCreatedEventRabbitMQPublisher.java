package com.jobee.admin.service.infrastructure.review.publisher;

import com.jobee.admin.service.domain.review.events.ReviewCreatedEventIntegration;
import com.jobee.admin.service.infrastructure.configuration.annotations.ReviewCreatedQueue;
import com.jobee.admin.service.infrastructure.services.EventService;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ReviewCreatedEventRabbitMQPublisher {

    private final EventService eventService;

    public ReviewCreatedEventRabbitMQPublisher(@ReviewCreatedQueue final EventService eventService) {
        this.eventService = Objects.requireNonNull(eventService);
    }

    @EventListener
    public void on(ReviewCreatedEventIntegration event) {
        this.eventService.send(event);
    }
}
