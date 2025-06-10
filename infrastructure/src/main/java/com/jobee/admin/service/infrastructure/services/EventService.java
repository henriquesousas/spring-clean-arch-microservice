package com.jobee.admin.service.infrastructure.services;

import com.jobee.admin.service.domain.events.IntegrationEvent;

public interface EventService {
    void send(IntegrationEvent<?> event) ;
}
