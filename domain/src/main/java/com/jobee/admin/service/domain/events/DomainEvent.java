package com.jobee.admin.service.domain.events;

import com.jobee.admin.service.domain.Identifier;

import java.time.Instant;

public interface DomainEvent {

    Instant occurredOn();

    default IntegrationEvent<?> getIntegrationEvent() {
        return null;
    }

    default Identifier aggregateId() {
        return null;
    }
}
