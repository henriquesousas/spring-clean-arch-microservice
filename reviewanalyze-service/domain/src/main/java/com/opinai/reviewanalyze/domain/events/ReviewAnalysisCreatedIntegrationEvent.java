package com.opinai.reviewanalyze.domain.events;


import com.opinai.shared.domain.events.IntegrationEvent;

import java.time.Instant;

public class ReviewAnalysisCreatedIntegrationEvent implements IntegrationEvent<ReviewAnalysisCreatedPayload> {

    private final ReviewAnalysisCreatedPayload payload;
    private final Instant occurredOn;
    private final int version;

    public ReviewAnalysisCreatedIntegrationEvent(final ReviewAnalysisCreatedPayload payload,
                                                 final Instant occurredOn,
                                                 final int version) {
        this.payload = payload;
        this.occurredOn = occurredOn;
        this.version = version;
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @Override
    public int eventVersion() {
        return version;
    }

    @Override
    public ReviewAnalysisCreatedPayload payload() {
        return this.payload;
    }
}
