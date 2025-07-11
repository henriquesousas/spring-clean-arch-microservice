package com.opinai.review.domain.events;

import com.opinai.review.domain.Review;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.events.DomainEvent;
import com.opinai.shared.domain.events.EventPayload;
import com.opinai.shared.domain.events.IntegrationEvent;

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
