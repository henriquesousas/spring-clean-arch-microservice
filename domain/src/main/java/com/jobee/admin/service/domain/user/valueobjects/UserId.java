package com.jobee.admin.service.domain.user.valueobjects;

import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.validation.Error;

import static java.util.UUID.randomUUID;

public class UserId extends Identifier {

    private final String value;

    private UserId(String value) {
        this.value = value;
        selfValidate();
    }

    public static UserId unique() {
        return new UserId(randomUUID().toString());
    }

    public static UserId from(String value) {
        return new UserId(value);
    }


    @Override
    protected void selfValidate() {
        if (this.value == null || this.value.isBlank()) {
            this.notification.append(new Error("UserId cannot be null or empty"));
        }

        if (!isValidUUID(value)) {
            this.notification.append(ValidationException.with(new Error("UserId must be a valid UUID")));
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
