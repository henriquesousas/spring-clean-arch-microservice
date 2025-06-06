package com.jobee.admin.service.domain.events;

import java.time.Instant;

public interface IntegrationEvent<T> {
    Instant occurredOn();

    T payload();
}
