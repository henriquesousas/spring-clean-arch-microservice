package com.jobee.admin.service.application;

import com.jobee.admin.service.domain.events.DomainEvent;

public interface DomainEventHandler {
    void handler(DomainEvent event);
}
