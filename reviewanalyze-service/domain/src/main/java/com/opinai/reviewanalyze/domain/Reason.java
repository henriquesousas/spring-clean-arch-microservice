package com.opinai.reviewanalyze.domain;


import com.opinai.shared.domain.ValueObject;
import com.opinai.shared.domain.validation.Error;

public class Reason extends ValueObject<String> {

    private static final int MAX_LENGTH = 30;
    private final String value;

    private Reason(final String value) {
        this.value = value;
        selfValidate();
    }

    public static Reason from(String value) {
        return new Reason(value);
    }

    private void selfValidate() {
        if (value.trim().length() > MAX_LENGTH) {
            this.getNotification().append(new Error("Número máximo de caracteres para o campo reason deve ser %s"
                    .formatted(MAX_LENGTH)));
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
