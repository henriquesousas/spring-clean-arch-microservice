package br.com.opinai.api.review.analise.domain.events;


import com.opinai.shared.domain.events.EventPayload;

public record ReviewAnalysisCreatedPayload(String aggregateId) implements EventPayload {
}

