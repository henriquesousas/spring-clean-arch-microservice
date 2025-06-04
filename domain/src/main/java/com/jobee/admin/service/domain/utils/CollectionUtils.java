package com.jobee.admin.service.domain.utils;

import com.jobee.admin.service.domain.review.valueobjects.Feedback;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T> String asString(Set<T> list, Function<T,String> mapper) {
        return list
                .stream()
                .map(mapper)
                .collect(Collectors.joining(","));
    }

    public static <IN, OUT> Set<OUT> asSet(Set<IN> values, Function<IN, OUT> mapper) {
        return values.stream()
                .map(mapper)
                .collect(Collectors.toSet());
    }

}
