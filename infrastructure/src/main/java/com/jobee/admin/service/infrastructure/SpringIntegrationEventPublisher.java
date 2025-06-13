package com.jobee.admin.service.infrastructure;

import com.jobee.admin.service.application.events.IntegrationEventPublisher;
import com.jobee.admin.service.domain.commons.events.EventPayload;
import com.jobee.admin.service.domain.commons.events.IntegrationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringIntegrationEventPublisher implements IntegrationEventPublisher {

    private final ApplicationEventPublisher event;

    private SpringIntegrationEventPublisher(final ApplicationEventPublisher event) {
        this.event = event;
    }

    public static SpringIntegrationEventPublisher from(final ApplicationEventPublisher eventPublisher) {
        return new SpringIntegrationEventPublisher(eventPublisher);
    }

    @Override
    public void publish(List<IntegrationEvent<? extends EventPayload>> events) {
        for (final var integrationEvent : events) {
            if (integrationEvent == null) return;
            event.publishEvent(integrationEvent);
        }
    }
}
