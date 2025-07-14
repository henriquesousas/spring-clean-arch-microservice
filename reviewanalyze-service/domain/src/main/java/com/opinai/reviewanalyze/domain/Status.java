package com.opinai.reviewanalyze.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Status {
    WAITING("WAITING"),
    IN_ANALYSIS("IN_ANALYSIS"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    public static Optional<Status> of(final String value) {
        return Arrays.stream(Status.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
