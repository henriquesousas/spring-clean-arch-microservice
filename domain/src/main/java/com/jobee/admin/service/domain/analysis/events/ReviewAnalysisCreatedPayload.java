package com.jobee.admin.service.domain.analysis.events;

import com.jobee.admin.service.domain.events.EventPayload;

public record ReviewAnalysisCreatedPayload(String aggregateId) implements EventPayload {
}

