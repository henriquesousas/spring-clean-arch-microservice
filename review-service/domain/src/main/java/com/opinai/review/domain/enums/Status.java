package com.opinai.review.domain.enums;

import com.opinai.shared.domain.Valuable;
import lombok.Getter;

@Getter
public enum Status implements Valuable<String> {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IN_ANALYSIS("IN_ANALYSIS");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

//    public static Optional<Status> of(final String label) {
//        return Arrays.stream(Status.values())
//                .filter(it -> it.name().equalsIgnoreCase(label))
//                .findFirst();
//    }
}
