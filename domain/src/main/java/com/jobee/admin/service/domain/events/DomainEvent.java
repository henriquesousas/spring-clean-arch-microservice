package com.jobee.admin.service.domain.commons.events;

import com.jobee.admin.service.domain.commons.Identifier;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public interface DomainEvent extends Serializable {

    Identifier aggregateId();

    Instant occurredOn();

    int eventVersion();

    default List<IntegrationEvent<? extends  EventPayload>> getIntegrationEvent() {
        return null;
    }
}
