package com.opinai.account.domain;

import java.util.Arrays;
import java.util.Optional;

public enum RoleType {
    USER,
    ADMIN,
    MODERATOR,
    SUPER_ADMIN;

    public static Optional<RoleType> of(final String label) {
        return Arrays.stream(RoleType.values())
                .filter(it -> it.name().equalsIgnoreCase(label))
                .findFirst();
    }
}
