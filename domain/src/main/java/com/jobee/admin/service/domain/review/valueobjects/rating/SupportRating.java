package com.jobee.admin.service.domain.review.valueobjects.rating;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.shared.ValueObject;

import java.util.Optional;

public class SupportRating extends ValueObject<Integer> {

    private final RatingScale scale;

    public SupportRating(RatingScale value) {
        this.scale = value;
    }

    @Override
    public Integer getValue() {
        return Optional.of(this.scale.getValue()).orElse(0);
    }
}
