package com.jobee.admin.service.application.events;

import com.jobee.admin.service.domain.commons.events.EventPayload;
import com.jobee.admin.service.domain.commons.events.IntegrationEvent;

import java.util.List;

public interface IntegrationEventPublisher {
     void publish(List<IntegrationEvent<? extends EventPayload>> events);
}
