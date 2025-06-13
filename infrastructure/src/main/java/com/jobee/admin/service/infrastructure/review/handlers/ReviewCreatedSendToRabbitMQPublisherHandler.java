package com.jobee.admin.service.infrastructure.review.handlers;

import com.jobee.admin.service.domain.review.events.ReviewCreatedEventIntegration;
import com.jobee.admin.service.infrastructure.configuration.annotations.ReviewCreatedQueue;
import com.jobee.admin.service.infrastructure.services.EventService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ReviewCreatedSendToRabbitMQPublisherHandler {

    private final EventService eventService;

    public ReviewCreatedSendToRabbitMQPublisherHandler(@ReviewCreatedQueue final EventService eventService) {
        this.eventService = Objects.requireNonNull(eventService);
    }

    @EventListener
    public void on(ReviewCreatedEventIntegration event) {
        System.out.println("Execute some logic when review has been created");
        this.eventService.send(event);
    }
}
