package com.jobee.admin.service.domain.events;

import com.jobee.admin.service.domain.Identifier;

import java.io.Serializable;
import java.time.Instant;

public interface DomainEvent extends Serializable {

    Instant occurredOn();

    default IntegrationEvent<?> getIntegrationEvent() {
        return null;
    }

    default Identifier aggregateId() {
        return null;
    }
}
