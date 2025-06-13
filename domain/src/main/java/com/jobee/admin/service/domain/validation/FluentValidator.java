package com.jobee.admin.service.domain.commons.validation;


public class FluentValidator {
    private final String value;
    private final ValidationHandler notification;

    public FluentValidator(String value, ValidationHandler notification) {
        this.value = value;
        this.notification = notification;
    }

    public FluentValidator minLength(int min, String message) {
        if (value != null && value.length() < min) {
            this.notification.append(new Error(message));
        }
        return this;
    }

    public FluentValidator notNullOrEmpty(String message) {
        if (value == null || value.isBlank()) {
            this.notification.append(new Error(message));
        }
        return this;
    }
}
