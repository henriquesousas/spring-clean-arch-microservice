package com.jobee.admin.service.domain.events;

import java.io.Serializable;
import java.time.Instant;

public interface IntegrationEvent<T> extends Serializable {
    Instant occurredOn();

    T payload();
}
