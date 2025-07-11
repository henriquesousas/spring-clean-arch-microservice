package com.opinai.shared.domain;

import com.opinai.shared.domain.validation.handler.Notification;
import lombok.Getter;

@Getter
public abstract class ValueObject<T> {
    protected final Notification notification;

    protected ValueObject() {
        this.notification = Notification.create();
    }

    public abstract T getValue();
}
