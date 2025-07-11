package com.opinai.review.domain.events;


import com.opinai.shared.domain.events.EventPayload;

public record ReviewCreatedEventPayload(
        String aggregateId,
        String userId,
        String productId,
        String type

) implements EventPayload {
}
