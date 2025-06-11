package com.jobee.admin.service.domain.core.review.events;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.events.DomainEvent;
import com.jobee.admin.service.domain.utils.InstantUtils;

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
