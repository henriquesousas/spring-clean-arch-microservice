package com.jobee.admin.service.infrastructure;

import com.jobee.admin.service.application.IntegrationEventPublisher;
import com.jobee.admin.service.domain.events.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IntegrationEventMediatorPublish implements IntegrationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    private IntegrationEventMediatorPublish(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public static IntegrationEventMediatorPublish of(final ApplicationEventPublisher eventPublisher) {
        return  new IntegrationEventMediatorPublish(eventPublisher);
    }


    @Override
    public void publishEvent(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            final var integrationEvent = event.getIntegrationEvent();
            if (integrationEvent == null) continue;
            eventPublisher.publishEvent(integrationEvent);
        }
    }
}

//    public void publish(final AggregateRoot<?> aggregateRoot) {
//        for (DomainEvent event : aggregateRoot.getUnDispatchedEvents()) {
//            aggregateRoot.markEventAsDispatched(event);
//            eventPublisher.publishEvent(event);
//        }
//    }


//    public void publishIntegrationEvents(final AggregateRoot<?> aggregateRoot) {
//        for (DomainEvent event : aggregateRoot.getEvents()) {
//            final var integrationEvent = event.getIntegrationEvent();
//            if (integrationEvent == null) continue;
//            eventPublisher.publishEvent(integrationEvent);
//        }
//    }