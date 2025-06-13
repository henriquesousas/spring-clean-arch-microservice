package com.jobee.admin.service.infrastructure.services;

import com.jobee.admin.service.domain.commons.events.EventPayload;
import com.jobee.admin.service.domain.commons.events.IntegrationEvent;

public interface EventService {
    void send(IntegrationEvent<? extends EventPayload> event) ;
}
