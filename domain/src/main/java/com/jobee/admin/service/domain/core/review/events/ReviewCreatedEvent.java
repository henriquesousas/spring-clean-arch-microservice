package com.jobee.admin.service.domain.core.review.events;

import com.jobee.admin.service.domain.core.review.Review;
import com.jobee.admin.service.domain.events.DomainEvent;
import com.jobee.admin.service.domain.utils.InstantUtils;

import java.time.Instant;

public class ReviewCreatedEvent implements DomainEvent {

    private final Review review;

    public ReviewCreatedEvent(Review review) {
        this.review = review;
    }

    @Override
    public Instant occurredOn() {
        return InstantUtils.now();
    }

    @Override
    public ReviewCreatedEventIntegration getIntegrationEvent() {
        return new ReviewCreatedEventIntegration(this.review);
    }
}
