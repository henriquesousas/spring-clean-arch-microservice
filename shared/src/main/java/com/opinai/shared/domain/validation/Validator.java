package com.opinai.shared.domain.validation;

import com.opinai.shared.domain.validation.handler.Notification;

public abstract class Validator {
    private final ValidationHandler handler;

    protected Validator(ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();

    protected ValidationHandler validationHandler() {
        return this.handler;
    }

    protected void copyIfHasError(final Notification notification) {
        if (notification == null) return;
        if (notification.hasError()) {
            this.validationHandler().copy(notification);
        }
    }
}
