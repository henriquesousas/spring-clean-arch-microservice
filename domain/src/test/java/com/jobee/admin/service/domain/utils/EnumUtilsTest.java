package com.jobee.admin.service.domain.utils;

import com.jobee.admin.service.domain.analysis.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumUtilsTest {

    @Test
    public void givenAValidEnum_whenCallOf_shouldReturnCorrectValue() {
        final var value = EnumUtils.of(Status.values(), "WAITING");
        Assertions.assertEquals("WAITING", value);
    }

    @Test
    public void givenAInValidEnum_whenCallOf_shouldReturnCorrectNull() {
        final var value = EnumUtils.of(Status.values(), "WAITING2");
        Assertions.assertNull(value);
    }
}
