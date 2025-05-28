package com.jobee.admin.service.domain.review.valueobjects.rating;

import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.shared.ValueObject;

public class AfterSalesRating extends ValueObject<Integer> {

    private final RatingOptions value;

    public AfterSalesRating(RatingOptions value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value.getValue();
    }
}
