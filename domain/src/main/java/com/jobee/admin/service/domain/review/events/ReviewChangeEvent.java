package com.jobee.admin.service.domain.review.events;

import com.jobee.admin.service.domain.commons.Identifier;
import com.jobee.admin.service.domain.commons.events.DomainEvent;
import com.jobee.admin.service.domain.commons.utils.InstantUtils;

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
