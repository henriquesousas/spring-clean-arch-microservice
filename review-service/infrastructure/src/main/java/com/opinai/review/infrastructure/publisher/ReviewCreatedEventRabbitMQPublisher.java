package com.opinai.review.infrastructure.publisher;

import com.opinai.review.domain.events.ReviewCreatedEventIntegration;
import com.opinai.review.infrastructure.annotations.ReviewCreatedQueue;
import com.opinai.shared.infrastructure.services.EventService;
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
