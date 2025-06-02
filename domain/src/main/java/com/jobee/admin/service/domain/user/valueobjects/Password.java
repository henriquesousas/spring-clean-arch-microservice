package com.jobee.admin.service.domain.user.valueobjects;

import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.validation.Error;

public class Password extends ValueObject<String> {

    private final String value;

    private Password(String value) {
        this.value = value;
        selfValidate();
    }

    public static Password from(String value) {
        return new Password(value);
    }


    protected void selfValidate() {
        if (value == null || value.isBlank()) {
            notification.append(new Error("Password inválido"));
        }

        if (value != null && value.length() < 8) {
            notification.append(new Error("Password deve ter pelo menos 8 caracteres"));
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
