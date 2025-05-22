package com.jobee.admin.service.domain.user.valueobjects;

import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.regex.Pattern;

public class Phone extends ValueObject<String> {

    private final String value;

    private static final Pattern CELULAR_PATTERN = Pattern.compile(
            "^\\(?\\d{2}\\)?\\s?(9\\d{4})[- ]?(\\d{4})$"
    );

    private Phone(String value) {
        this.value = value;
        selfValidate();
    }

    public static Phone from(String value) {
        return new Phone(value);
    }

    @Override
    protected void selfValidate() {
        final var isValid = value != null && CELULAR_PATTERN.matcher(value).matches();
        if (!isValid) {
            notification.append(new Error("Número de telefone inválido"));
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
