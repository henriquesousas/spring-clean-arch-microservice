package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.handler.Notification;


import java.util.Objects;
import java.util.UUID;

public class CategoryId extends Identifier {

    private final String value;

    private CategoryId(String value) {
        this.value = Objects.requireNonNull(value);
        selfValidate();
    }

    public static CategoryId unique() {
        return CategoryId.from(UUID.randomUUID().toString());
    }

    public static CategoryId from(final String id) {
        return new CategoryId(id);
    }

    public static CategoryId from(final UUID uuid) {
        return new CategoryId(uuid.toString().toLowerCase());
    }

    @Override
    protected void selfValidate() {

        if (value == null || value.isEmpty()) {
            this.notification.append(ValidationException.with(new Error("CategoryId cannot be null or empty")));
        }

        if (!isValidUUID(value)) {
            this.notification.append(ValidationException.with(new Error("CategoryId must be a valid UUID")));
        }
    }


    @Override
    public String getValue() {
        return value;
    }
}
