package com.jobee.admin.service.domain.review.events;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.utils.IdUtils;

public class EventId extends Identifier {
    public EventId(String value) {
        super(value);
    }

    public static EventId unique() {
        return new EventId(IdUtils.uuid());
    }

    public static EventId from(String value) {
        return new EventId(value);
    }
}
