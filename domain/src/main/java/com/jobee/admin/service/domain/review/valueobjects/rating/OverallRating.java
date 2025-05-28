package com.jobee.admin.service.domain.review.valueobjects.rating;

import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;

public class OverallRating extends ValueObject<Integer> {

    private final RatingOptions value;

    public OverallRating(RatingOptions value) {
        this.value = value;
        selfValidate();
    }


    private void selfValidate() {
        if (this.value == null) {
            this.notification.append(new Error("Avaliação geral é obrigatória"));
        }
    }

    @Override
    public Integer getValue() {
        return this.value.getValue();
    }
}
