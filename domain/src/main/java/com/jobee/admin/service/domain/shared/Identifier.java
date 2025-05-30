package com.jobee.admin.service.domain.shared;

import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.Objects;
import java.util.UUID;

public abstract class Identifier extends ValueObject<String> {

    private static final int MAX_LENGTH = 32;
    private final String value;

    public Identifier(String value) {
        this.value = value;
        selfValidate();
    }

    protected void selfValidate() {
        if (this.getValue() == null || this.getValue().isBlank()) {
            this.notification.append(new Error("%s cannot be null or empty".formatted(
                    this.getClass().getSimpleName()
            )));
        }

        if (this.getValue() != null && this.getValue().length() != MAX_LENGTH) {
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
