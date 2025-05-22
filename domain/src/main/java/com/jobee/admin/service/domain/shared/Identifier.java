package com.jobee.admin.service.domain.shared;

import java.util.Objects;
import java.util.UUID;

public abstract class Identifier extends ValueObject<String> {

    public abstract String getValue();

    protected Boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
