package com.jobee.admin.service.domain.core.review.events;

import com.jobee.admin.service.domain.core.review.Review;
import com.jobee.admin.service.domain.events.IntegrationEvent;
import com.jobee.admin.service.domain.utils.InstantUtils;

import java.time.Instant;

public class ReviewCreatedEventIntegration implements IntegrationEvent<Review> {

    private final Review review;

    public ReviewCreatedEventIntegration(Review review) {
        this.review = review;
    }

    @Override
    public Instant occurredOn() {
        return InstantUtils.now();
    }

    @Override
    public Review payload() {
        return review;
    }
}
