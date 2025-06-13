package com.jobee.admin.service.domain.review.events;

import com.jobee.admin.service.domain.commons.events.EventPayload;

public record ReviewCreatedPayload(
        String aggregateId
) implements EventPayload {
}
