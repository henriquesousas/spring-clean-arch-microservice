package com.jobee.admin.service.domain.category;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.exceptions.ValueObjectValidationError;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.handler.Notification;


import java.util.Objects;
import java.util.UUID;

public class CategoryId extends Identifier {
    //TODO: Mover para a classe principal
    private final Notification notification;
    private final String value;

    private CategoryId(String value) {
        Objects.requireNonNull(value);
        this.value = value;
        this.notification = Notification.create();
        this.validate();
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

    //TODO: Refactor
    public void validate() {
        if (value == null || value.isEmpty()) {
            this.notification.append(ValidationException.with(new Error("CategoryId cannot be null or empty")));
        }

        if (!this.isValidUUID(value)) {
            this.notification.append(ValidationException.with(new Error("CategoryId must be a valid UUID")));
        }
    }

    public Notification getNotification() {
        return notification;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryId that = (CategoryId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    private   boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
