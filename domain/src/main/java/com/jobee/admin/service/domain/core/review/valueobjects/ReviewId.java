package com.jobee.admin.service.domain.core.review.valueobjects;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.utils.IdUtils;

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
