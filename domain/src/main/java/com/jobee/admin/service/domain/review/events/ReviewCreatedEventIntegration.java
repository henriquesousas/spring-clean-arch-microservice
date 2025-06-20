package com.jobee.admin.service.domain.review.events;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.events.IntegrationEvent;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.utils.InstantUtils;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class ReviewCreatedEventIntegration implements IntegrationEvent<ReviewCreatedPayload> {

    private final ReviewCreatedPayload payload;
    private final String eventId;
    private final Instant occurredOn;
    private final String serviceOrigin;
    private final int eventVersion;

    private ReviewCreatedEventIntegration(final ReviewCreatedPayload payload) {
        this.payload = Objects.requireNonNull(payload);
        this.occurredOn = InstantUtils.now();
        this.eventVersion = 1;
        this.eventId = EventId.unique().getValue();
        this.serviceOrigin = "CreateReviewUseCase";
    }

    public static ReviewCreatedEventIntegration from(Review review) {
        final var payload = new ReviewCreatedPayload(
                review.getAggregateId().getValue(),
                review.getUserId().getValue(),
                "CREATE"
        );
        return new ReviewCreatedEventIntegration(payload);
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
