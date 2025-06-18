package com.jobee.admin.service.domain.review.events;


import com.jobee.admin.service.domain.events.EventPayload;

public record ReviewCreatedPayload(
        String aggregateId,
        String userId,
        String type

) implements EventPayload {
}
