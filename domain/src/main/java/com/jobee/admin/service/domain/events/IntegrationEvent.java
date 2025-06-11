package com.jobee.admin.service.domain.events;

import java.io.Serializable;
import java.time.Instant;

    public interface IntegrationEvent<T extends EventPayload> extends Serializable {
    Instant occurredOn();

    int eventVersion();

    T payload();
}
