package com.jobee.admin.service.domain.shared;

import com.jobee.admin.service.domain.shared.validation.handler.Notification;
import lombok.Getter;

@Getter
public abstract class ValueObject<T> {
    protected final Notification notification;

    protected ValueObject() {
        this.notification = Notification.create();
    }

//    protected abstract void selfValidate() ;

    public abstract T getValue();

}
