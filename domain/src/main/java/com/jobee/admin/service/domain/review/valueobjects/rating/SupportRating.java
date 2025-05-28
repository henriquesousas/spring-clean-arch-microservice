package com.jobee.admin.service.domain.review.valueobjects.rating;

import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.shared.ValueObject;

public class SupportRating extends ValueObject<Integer> {

    private final RatingOptions value;

    public SupportRating(RatingOptions value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value.getValue();
    }
}
