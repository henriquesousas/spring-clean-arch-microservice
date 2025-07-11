package com.opinai.shared.infrastructure.services;


import com.opinai.shared.domain.events.EventPayload;
import com.opinai.shared.domain.events.IntegrationEvent;

public interface EventService {
    void send(IntegrationEvent<? extends EventPayload> event) ;
}
