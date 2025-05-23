package com.jobee.admin.service.domain.review.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum RatingOptions {

    RA_1(1),
    RA_2(2),
    RA_3(3),
    RA_4(4),
    RA_5(5);

    private final int value;

    RatingOptions(final int value) {
        this.value = value;
    }

    public static Optional<RatingOptions> of(final int point) {
        return Arrays.stream(RatingOptions.values())
                .filter(it -> it.value == point)
                .findFirst();
    }
}