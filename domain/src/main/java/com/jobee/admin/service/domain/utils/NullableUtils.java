package com.jobee.admin.service.domain.commons.utils;

import java.util.Optional;
import java.util.function.Function;

public class NullableUtils {

    public static <T, R> R mapOrNull(T value, Function<T, R> mapper) {
        return Optional.ofNullable(value)
                .map(mapper)
                .orElse(null);
    }
}
