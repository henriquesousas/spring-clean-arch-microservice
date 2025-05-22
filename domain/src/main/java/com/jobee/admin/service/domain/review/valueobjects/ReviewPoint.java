package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.shared.ValueObject;

public class ReviewPoint extends ValueObject<String> {

    private final String value;

    private ReviewPoint(String value) {
        this.value = value;
        selfValidate();
    }

    public static ReviewPoint of(String value) {
        return new ReviewPoint(value);
    }

    @Override
    protected void selfValidate() {
        if (this.value == null || this.value.isBlank()) {
            throw new IllegalArgumentException("Review point cannot be null or empty");
        }

        if (this.value.length() > 30) {
            throw new IllegalArgumentException("Review point cannot exceed 30 characters");
        }

    }

    @Override
    public String getValue() {
        return this.value;
    }
}
