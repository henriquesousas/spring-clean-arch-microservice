package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.utils.IdUtils;
import com.jobee.admin.service.domain.shared.validation.Error;

import static java.util.UUID.randomUUID;

public class ReviewId extends Identifier {

    public ReviewId(String value) {
        super(value);
    }

    public static ReviewId unique() {
        return new ReviewId(IdUtils.uuid());
    }

    public static ReviewId from(String value) {
        return new ReviewId(value);
    }
}
