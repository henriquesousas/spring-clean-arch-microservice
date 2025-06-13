package com.jobee.admin.service.domain.review.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Type {
    PRODUCT("PRODUCT"),
    SERVICE("SERVICE");

    private final String value;

    Type(final String value) {
        this.value = value;
    }

    public static Optional<Type> of(final String value) {
        return Arrays.stream(Type.values())
                .filter(it -> it.value.equals(value))
                .findFirst();
    }
}
