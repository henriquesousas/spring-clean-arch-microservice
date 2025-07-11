package com.opinai.review.domain.valueobjects;

import com.opinai.shared.domain.utils.IdUtils;
import com.opinai.shared.domain.Identifier;

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
