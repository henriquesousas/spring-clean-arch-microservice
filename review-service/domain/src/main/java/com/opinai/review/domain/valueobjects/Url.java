package com.opinai.review.domain.valueobjects;

import com.opinai.shared.domain.ValueObject;
import com.opinai.shared.domain.validation.Error;

import java.net.URL;
import java.util.Objects;

public class Url extends ValueObject<String> {

    private final String value;

    private Url(String value) {
        this.value = Objects.requireNonNull(value);
        selfValidate();
    }

    public static Url from(final String value) {
        return new Url(value);
    }

    protected void selfValidate() {
        try {
            new URL(this.value).toURI();
        } catch (Exception e) {
            this.notification.append(new Error("Url inválida"));
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
