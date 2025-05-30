package com.jobee.admin.service.domain.review.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Type {
    PRODUCT(0),
    SERVICE(1);

    private final int value;

    Type(final int value) {
        this.value = value;
    }

    public static Optional<Type> of(final int value) {
        return Arrays.stream(Type.values())
                .filter(it -> it.value == value)
                .findFirst();
    }
}
