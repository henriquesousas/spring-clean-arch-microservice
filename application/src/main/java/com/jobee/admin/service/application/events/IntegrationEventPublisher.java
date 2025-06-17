package com.jobee.admin.service.application.events;


import com.jobee.admin.service.domain.events.EventPayload;
import com.jobee.admin.service.domain.events.IntegrationEvent;

import java.util.List;


public interface IntegrationEventPublisher {
     void publish(List<IntegrationEvent<? extends EventPayload>> events);
}
