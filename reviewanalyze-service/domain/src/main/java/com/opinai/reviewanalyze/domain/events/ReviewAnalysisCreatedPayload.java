package com.opinai.reviewanalyze.domain.events;


import com.opinai.shared.domain.events.EventPayload;

public record ReviewAnalysisCreatedPayload(String aggregateId) implements EventPayload {
}

