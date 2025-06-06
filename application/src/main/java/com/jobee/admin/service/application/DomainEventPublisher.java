package com.jobee.admin.service.application;

import com.jobee.admin.service.domain.events.DomainEvent;

import java.util.List;

//TODO: Rename to EventPublisher
public interface DomainEventPublisher {
    public void publishEvent(List<DomainEvent> events);
}
