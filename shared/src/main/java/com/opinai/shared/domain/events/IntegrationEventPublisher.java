package com.opinai.shared.domain.events;

import java.util.List;

public interface IntegrationEventPublisher {
     void publish(List<IntegrationEvent<? extends EventPayload>> events);
}
