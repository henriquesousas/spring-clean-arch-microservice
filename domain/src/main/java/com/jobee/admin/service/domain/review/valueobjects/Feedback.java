package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.commons.ValueObject;
import com.jobee.admin.service.domain.commons.validation.Error;

import java.util.Objects;

public class Feedback extends ValueObject<String> {


    protected static final int MAX_CHARACTER = 30;
    protected String value;

    private Feedback(String value) {
        this.value = value;
        selfValidate();
    }

    public static Feedback from(String value) {
        return new Feedback(value);
    }

    private void selfValidate() {

        if (this.value == null || this.value.trim().isEmpty()) {
            this.notification.append(new Error("Feedback inválido"));
        }

        if (this.value != null && this.value.length() > MAX_CHARACTER) {
            this.notification.append(
                    new Error("Ponto excedeu o limite de " + MAX_CHARACTER + " caracteres: \"" + this.value + "\""));
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback feedback)) return false;
        return Objects.equals(value, feedback.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
