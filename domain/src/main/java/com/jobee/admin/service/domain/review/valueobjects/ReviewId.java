package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.validation.Error;

import static java.util.UUID.randomUUID;

public class ReviewId extends Identifier {

    private final String value;

    public ReviewId(String value) {
        this.value = value;
        selfValidate();
    }

    public static ReviewId unique() {
        return new ReviewId(randomUUID().toString());
    }

    public static ReviewId from(String value) {
        return new ReviewId(value);
    }

    protected void selfValidate() {
        if (this.value == null || this.value.isBlank()) {
            this.notification.append(new Error("ReviewId cannot be null or empty"));
        }

        if (!isValidUUID(value)) {
            this.notification.append(ValidationException.with(new Error("ReviewId must be a valid UUID")));
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
