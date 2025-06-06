package com.jobee.admin.service.application;

import com.jobee.admin.service.domain.events.DomainEvent;

import java.util.List;

public class DomainEventMediatorPublisher implements DomainEventPublisher {

    private final IntegrationEventPublisher integrationEvent;
    private final List<DomainEventHandler> handlers;


    private DomainEventMediatorPublisher(
            final IntegrationEventPublisher integrationEvent,
            final List<DomainEventHandler> handlers) {
        this.integrationEvent = integrationEvent;
        this.handlers = handlers;
    }

    public static DomainEventMediatorPublisher of(final IntegrationEventPublisher publisher, final List<DomainEventHandler> handlers) {
        return new DomainEventMediatorPublisher(publisher, handlers);
    }


    @Override
    public void publishEvent(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            for (DomainEventHandler handler : handlers) {
                handler.handler(event);
            }
        }

        this.integrationEvent.publishEvent(events);
    }
}
