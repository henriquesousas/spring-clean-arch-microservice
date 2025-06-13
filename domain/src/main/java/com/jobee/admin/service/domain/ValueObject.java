package com.jobee.admin.service.domain;

import com.jobee.admin.service.domain.commons.validation.handler.Notification;
import lombok.Getter;

@Getter
public abstract class ValueObject<T> {
    protected final Notification notification;

    protected ValueObject() {
        this.notification = Notification.create();
    }

    public abstract T getValue();

}
