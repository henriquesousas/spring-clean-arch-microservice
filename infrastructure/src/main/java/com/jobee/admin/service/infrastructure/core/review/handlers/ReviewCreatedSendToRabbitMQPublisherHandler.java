package com.jobee.admin.service.infrastructure.core.review.handlers;

import com.jobee.admin.service.domain.core.review.events.ReviewCreatedEventIntegration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewCreatedSendToRabbitMQPublisherHandler {

    @EventListener
    public void on(ReviewCreatedEventIntegration event) {
        System.out.println("Execute some logic when review has been created");
    }
}
