package com.opinai.reviewanalyze.domain.events;

import com.opinai.reviewanalyze.domain.ReviewAnalysis;
import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.events.DomainEvent;
import com.opinai.shared.domain.events.EventPayload;
import com.opinai.shared.domain.events.IntegrationEvent;
import com.opinai.shared.domain.utils.InstantUtils;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class ReviewAnalysisStartedEvent implements DomainEvent {

    private final ReviewAnalysis reviewAnalysis;
    private final Instant occurredOn;

    public ReviewAnalysisStartedEvent( ReviewAnalysis reviewAnalysis) {
        this.reviewAnalysis = Objects.requireNonNull(reviewAnalysis);
        this.occurredOn = InstantUtils.now();

    }

    @Override
    public Identifier aggregateId() {
        return this.reviewAnalysis.getAggregateId();
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @Override
    public int eventVersion() {
        return 1;
    }

    @Override
    public List<IntegrationEvent<? extends EventPayload>> getIntegrationEvent() {
        final var payload= new ReviewAnalysisCreatedPayload(this.aggregateId().getValue());
        return List.of(new ReviewAnalysisCreatedIntegrationEvent(payload, this.occurredOn(), this.eventVersion()));
    }
}
