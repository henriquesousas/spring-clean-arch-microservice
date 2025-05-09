package com.jobee.admin.service.domain.genre;

import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.UUID;

public class GenreId extends Identifier {

    private final String value;

    private GenreId(String value) {
        this.value = value;
        selfValidate();
    }

    public static GenreId unique() {
        return new GenreId(UUID.randomUUID().toString());
    }

    public static GenreId from(String value) {
        return new GenreId(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    protected void selfValidate() {
        if (this.value == null || this.value.isBlank()) {
            this.notification.append(new Error("GenreId cannot be null or empty"));
        }

        if (!isValidUUID(value)) {
            this.notification.append(ValidationException.with(new Error("GenreId must be a valid UUID")));
        }
    }
}
