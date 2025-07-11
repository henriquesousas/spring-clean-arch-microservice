package com.jobee.admin.service.domain.review.events;


import com.jobee.admin.service.domain.events.EventPayload;

public record ReviewCreatedEventPayload(
        String aggregateId,
        String userId,
        String productId,
        String type

) implements EventPayload {
}
