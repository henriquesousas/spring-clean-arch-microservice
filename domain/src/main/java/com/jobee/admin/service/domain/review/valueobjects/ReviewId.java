package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.commons.Identifier;
import com.jobee.admin.service.domain.commons.utils.IdUtils;

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
