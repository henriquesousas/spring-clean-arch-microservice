package com.jobee.admin.service.domain.review.valueobjects;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.validation.Error;
import lombok.Getter;

@Getter
public class Rating extends ValueObject<Rating> {
    private final RatingScale overall;
    private final RatingScale postSale;
    private final RatingScale responseTime;

    private Rating(RatingScale overall, RatingScale postSale, RatingScale responseTime) {
        super();
        this.overall = overall;
        this.postSale = postSale;
        this.responseTime = responseTime;
        selfValidate();
    }

    public static Rating from(RatingScale overall, RatingScale postSale, RatingScale responseTime) {
        return new Rating(overall, postSale, responseTime);
    }

    private void selfValidate() {
        if (overall == null) {
            this.notification.append(new Error("É necessário informar a nota geral"));
        }
    }

    @Override
    public Rating getValue() {
        return this;
    }
}
