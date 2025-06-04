package com.jobee.admin.service.domain.utils;

import com.jobee.admin.service.domain.review.enums.RatingScale;

import java.util.Optional;
import java.util.function.Function;

public class NullableUtils {

    public static <T, R> R mapOrNull(T value, Function<T, R> mapper) {
        return Optional.ofNullable(value)
                .map(mapper)
                .orElse(null);
    }
}
