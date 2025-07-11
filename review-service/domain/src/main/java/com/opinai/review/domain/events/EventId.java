package com.opinai.review.domain.events;

import com.opinai.shared.domain.utils.IdUtils;
import com.opinai.shared.domain.Identifier;

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
