package com.jobee.admin.service.application.events;

import com.jobee.admin.service.domain.AggregateRoot;
import com.jobee.admin.service.domain.events.DomainEvent;

import java.util.List;
import java.util.Objects;

public class DomainEventMediator {

    private final IntegrationEventPublisher integrationEvent;
    private final List<DomainEventHandler> handlers;


    private DomainEventMediator(
            final IntegrationEventPublisher integrationEvent,
            final List<DomainEventHandler> handlers
    ) {
        this.integrationEvent = Objects.requireNonNull(integrationEvent);
        this.handlers = Objects.requireNonNull(handlers);
    }

    public static DomainEventMediator from(final IntegrationEventPublisher publisher, final List<DomainEventHandler> handlers) {
        return new DomainEventMediator(publisher, handlers);
    }

    public void publishEvent(AggregateRoot<?> aggregateRoot) {
        for (DomainEvent event : aggregateRoot.getEvents()) {
            for (DomainEventHandler handler : handlers) {
                handler.handler(event);
            }

            final var integrationEvent = event.getIntegrationEvent();
            if (integrationEvent == null) return;
            this.integrationEvent.publish(event.getIntegrationEvent());
        }

        aggregateRoot.clearEvents();
    }
}
