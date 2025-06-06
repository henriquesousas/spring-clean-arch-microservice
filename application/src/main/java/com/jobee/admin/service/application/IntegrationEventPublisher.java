package com.jobee.admin.service.application;

import com.jobee.admin.service.domain.events.DomainEvent;

import java.util.List;

public interface IntegrationEventPublisher {
    public void publishEvent(List<DomainEvent> events);
}
