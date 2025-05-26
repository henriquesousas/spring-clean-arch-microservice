package com.jobee.admin.service.domain.review.valueobjects;


import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.net.URL;
import java.util.Objects;

public class LinkSite extends ValueObject<String> {

    private final String value;
    private LinkSite(String value) {
        this.value = Objects.requireNonNull(value);
        selfValidate();
    }


    public static LinkSite from(final String value) {
        return new LinkSite(value);
    }

    @Override
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
