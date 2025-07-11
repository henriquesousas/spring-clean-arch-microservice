package com.opinai.shared.domain.utils;

import com.opinai.shared.domain.Valuable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

enum DummyIntegerEnum implements Valuable<Integer> {
    VALUE1(1),
    VALUE2(2);

    private final Integer value;

    DummyIntegerEnum(final Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

public class EnumUtilsOf {
    
    @Test
    public void givenAValidIntegerEnum_whenCallMethod_shouldReturnCorrectValue() {
        final var value = EnumUtils.of(DummyIntegerEnum.values(), 1);
        Assertions.assertEquals(1, value.getValue());
        Assertions.assertInstanceOf(DummyIntegerEnum.class, value);
    }

    @Test
    public void givenAInValidIntegerEnum_whenCallMethod_shouldReturnNull() {
        final var value = EnumUtils.of(DummyIntegerEnum.values(), 10);
        Assertions.assertNull(value);
    }
}
