package com.jobee.admin.service.domain.review.valueobjects;


import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.validation.Error;

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
