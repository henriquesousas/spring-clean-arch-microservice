package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.review.enums.Score;
import com.jobee.admin.service.domain.validation.Error;
import lombok.Getter;

@Getter
public class Rating extends ValueObject<Integer> {
    private final Score value;

    private Rating(Score value) {
        this.value = value;
        selfValidate();
    }

    public static Rating from(Score value) {
        return new Rating(value);
    }

    private void selfValidate() {
        if (value == null) {
            this.notification.append(new Error("É necessário informar a nota geral"));
        }
    }

    public Integer getValue() {
        if(this.value == null) return null;
        return value.getValue();
    }
}
