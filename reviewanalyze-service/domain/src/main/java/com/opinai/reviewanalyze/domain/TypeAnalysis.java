package com.opinai.reviewanalyze.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum TypeAnalysis {
    CREATE("CREATE"),
    UPDATE("UPDATE")    ;

    private final String value;

    TypeAnalysis(String value) {
        this.value = value;
    }

    public static Optional<Status> of(final String value) {
        return Arrays.stream(Status.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
