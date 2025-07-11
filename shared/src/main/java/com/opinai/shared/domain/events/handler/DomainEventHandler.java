package com.opinai.shared.domain.events.handler;

import com.opinai.shared.domain.events.DomainEvent;

public interface DomainEventHandler {
    void handler(DomainEvent event);
}
