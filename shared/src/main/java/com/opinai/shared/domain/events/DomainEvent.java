package com.opinai.shared.domain.events;

import com.opinai.shared.domain.Identifier;

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
