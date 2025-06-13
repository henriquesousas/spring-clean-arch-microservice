package com.jobee.admin.service.domain.utils;


import java.util.Arrays;
import java.util.Optional;

public class EnumUtils {

    public static <T extends Enum<T>> Optional<T> of(T[] values, final String value) {
        return Arrays.stream(values)
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
