package com.opinai.shared.domain.utils;

import com.opinai.shared.domain.Valuable;

import java.util.Arrays;
import java.util.Objects;

public class EnumUtils {

    public static <T extends Enum<T> & Valuable<I>, I> T of(T[] values, I value) {
        return Arrays.stream(values)
                .filter(it -> Objects.equals(it.getValue(), value))
                .findFirst()
                .orElse(null);
    }


//    public static <T extends Enum<T>, I> String asStr(T[] values, final I value) {
//        return Arrays.stream(values)
//                .filter(it -> it.name() == value)
//                .findFirst()
//                .map(t -> t.name())
//                .orElse(null);
//    }
}
