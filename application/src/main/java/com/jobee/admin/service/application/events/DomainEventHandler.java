package com.jobee.admin.service.application.events;

import com.jobee.admin.service.domain.events.DomainEvent;

public interface DomainEventHandler {
    void handler(DomainEvent event);
}
