package com.jobee.admin.service.domain.utils;

import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtilsAsStringTest {

    @Test
    public void givenAValidValues_whenCallMethod_shouldReturnString() {
        final var expectedValue = "a,b,c";
        final var values = Set.of("a", "b", "c");
        final var value = CollectionUtils.asString(values, it -> it.trim());
        final var value2 = CollectionUtils.asString(values);

        Assertions.assertEquals(expectedValue, value);
        Assertions.assertEquals(expectedValue, value2);

    }

    @Test
    public void givenAValidValues_whenCallMethodasSet_shouldReturnSet() {
        final var value = "a,b,c";
        final var expectedValue = Set.of("a", "b", "c");

        final var result = CollectionUtils.asSet(value.split(","), String::toString);

        Assertions.assertEquals(result, expectedValue);
    }
}
