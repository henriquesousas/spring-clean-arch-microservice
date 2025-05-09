package com.jobee.admin.service.domain.shared;

import com.jobee.admin.service.domain.shared.validation.handler.Notification;

public abstract class ValueObject {
    protected final Notification notification;

    protected ValueObject() {
        this.notification = Notification.create();
    }

    protected abstract void selfValidate() ;

    public Notification getNotification() {
        return notification;
    }
}
