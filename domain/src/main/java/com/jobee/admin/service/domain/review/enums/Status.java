package com.jobee.admin.service.domain.review.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Status {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IN_ANALYSIS("IN_ANALYSIS");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    public static Optional<Status> of(final String label) {
        return Arrays.stream(Status.values())
                .filter(it -> it.name().equalsIgnoreCase(label))
                .findFirst();
    }
}
