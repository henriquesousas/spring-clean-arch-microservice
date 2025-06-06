package com.jobee.admin.service.domain.core.review.events;

import com.jobee.admin.service.domain.events.DomainEvent;
import com.jobee.admin.service.domain.utils.InstantUtils;

import java.time.Instant;

public class ReviewChangeEvent implements DomainEvent {

    @Override
    public Instant occurredOn() {
        return InstantUtils.now();
    }
}
