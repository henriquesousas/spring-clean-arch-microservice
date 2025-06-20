package com.jobee.admin.service.domain;

import com.jobee.admin.service.domain.reviewanalysis.Reason;
import com.jobee.admin.service.domain.validation.handler.Notification;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class ValueObject<T> {
    protected final Notification notification;

    protected ValueObject() {
        this.notification = Notification.create();
    }

    public abstract T getValue();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reason reason)) return false;
        return Objects.equals(getValue(), reason.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

}
