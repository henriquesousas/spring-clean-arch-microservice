package com.jobee.admin.service.domain;


import com.jobee.admin.service.domain.validation.Error;

import java.util.Objects;

public abstract class Identifier extends ValueObject<String> {

    private static final int MAX_LENGTH = 32;
    protected final String value;

    public Identifier(String value) {
        selfValidate(value);
        this.value = value;
    }

    protected void selfValidate(String value) {
        if (value == null || value.isBlank()) {
            this.notification.append(new Error("%s cannot be null or empty".formatted(
                    this.getClass().getSimpleName()
            )));
        }

        if (value != null && value.length() != MAX_LENGTH) {
            this.notification.append(new Error("%s must be a valid UUID".formatted(
                    this.getClass().getSimpleName()
            )));
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
