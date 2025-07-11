package com.opinai.review.domain.events;

import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.events.DomainEvent;

import java.time.Instant;

public class ReviewChangeEvent implements DomainEvent {

    @Override
    public Identifier aggregateId() {
        return null;
    }

    @Override
    public Instant occurredOn() {
        return InstantUtils.now();
    }

    @Override
    public int eventVersion() {
        return 0;
    }
}
