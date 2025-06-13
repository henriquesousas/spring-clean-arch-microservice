package com.jobee.admin.service.domain.review.events;

import com.jobee.admin.service.domain.commons.Identifier;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.commons.events.DomainEvent;
import com.jobee.admin.service.domain.commons.events.EventPayload;
import com.jobee.admin.service.domain.commons.events.IntegrationEvent;
import com.jobee.admin.service.domain.commons.utils.InstantUtils;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class ReviewCreatedEvent implements DomainEvent {

    private final Review review;
    private final Instant occurredOn;
    private final int eventVersion;

    public ReviewCreatedEvent(Review review) {
        this.review = Objects.requireNonNull(review);
        this.occurredOn = InstantUtils.now();
        this.eventVersion = 1;
    }

    @Override
    public Identifier aggregateId() {
        return this.review.getAggregateId();
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @Override
    public int eventVersion() {
        return this.eventVersion;
    }

    @Override
    public List<IntegrationEvent<? extends EventPayload>> getIntegrationEvent() {
        return List.of(ReviewCreatedEventIntegration.from(this.review));
    }
}
