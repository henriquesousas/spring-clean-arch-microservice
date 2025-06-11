package com.jobee.admin.service.domain.core.review.events;

import com.jobee.admin.service.domain.events.EventPayload;

public record ReviewCreatedPayload(
        String aggregateId
) implements EventPayload {
}
