package com.opinai.review.domain.enums;

import com.opinai.shared.domain.Valuable;

public enum Score implements Valuable<Integer> {


    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;

    Score(final int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}