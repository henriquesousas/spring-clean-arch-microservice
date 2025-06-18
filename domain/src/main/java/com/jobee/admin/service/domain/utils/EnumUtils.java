package com.jobee.admin.service.domain.utils;


import com.jobee.admin.service.domain.analysis.Status;

import java.util.Arrays;
import java.util.Optional;

public class EnumUtils {

//    public static <T extends Enum<T>> T of(T[] values, final String value) {
//        return Arrays.stream(values)
//                .filter(it -> it.name().equalsIgnoreCase(value))
//                .findFirst()
//                .orElse(null);
//    }

    public static <T extends Enum<T>> String of(T[] values, final String value) {
        return Arrays.stream(values)
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .map(t -> t.name())
                .orElse(null);
    }
}
