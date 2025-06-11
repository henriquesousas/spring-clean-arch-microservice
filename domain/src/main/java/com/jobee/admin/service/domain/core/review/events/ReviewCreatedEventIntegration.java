package com.jobee.admin.service.domain.core.review.events;

import com.jobee.admin.service.domain.core.review.Review;
import com.jobee.admin.service.domain.events.IntegrationEvent;
import com.jobee.admin.service.domain.utils.InstantUtils;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class ReviewCreatedEventIntegration implements IntegrationEvent<ReviewCreatedPayload> {

    private final ReviewCreatedPayload payload;
    private final Instant occurredOn;
    private final int eventVersion;

    private ReviewCreatedEventIntegration(final ReviewCreatedPayload payload) {
        this.payload = Objects.requireNonNull(payload);
        this.occurredOn = InstantUtils.now();
        this.eventVersion = 1;
    }

    public static ReviewCreatedEventIntegration from(Review review) {
        return new ReviewCreatedEventIntegration(new ReviewCreatedPayload(review.getAggregateId().getValue()));
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
    public ReviewCreatedPayload payload() {
        return this.payload;
    }


}
