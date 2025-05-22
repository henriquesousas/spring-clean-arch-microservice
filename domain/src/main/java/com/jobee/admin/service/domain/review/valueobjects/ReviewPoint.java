package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.Objects;

public class ReviewPoint extends ValueObject<String> {

    private final String value;

    private ReviewPoint(String value) {
        this.value = value;
        selfValidate();
    }

    public static ReviewPoint of(String value) {
        return new ReviewPoint(value);
    }

    @Override
    protected void selfValidate() {
        if (this.value == null || this.value.isBlank()) {
            this.notification.append(new Error("Review não pode ser nulo ou vazio"));

        }

        if (this.value != null && this.value.length() > 30) {
            this.notification.append(new Error("Review não pode exceder 30 caracteres"));
        }

    }

    @Override
    public String getValue() {
        return this.value;
    }

    // TODO: Refactor
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ReviewPoint that = (ReviewPoint) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
