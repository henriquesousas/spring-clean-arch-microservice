package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;
import lombok.Getter;

import java.util.Optional;

@Getter
public class Rating extends ValueObject<Rating> {
    private final RatingOptions overallRating;
    private final RatingOptions supportResponseTimeRating;
    private final RatingOptions afterSalesServiceRating;

    private Rating(RatingOptions overallRating, RatingOptions supportResponseTimeRating, RatingOptions afterSalesServiceRating) {
        super();
        this.overallRating = overallRating;
        this.supportResponseTimeRating = supportResponseTimeRating;
        this.afterSalesServiceRating = afterSalesServiceRating;
        selfValidate();
    }

    public static Rating newRating(
            RatingOptions overallRating
    ) {
        return new Rating(overallRating, null, null);
    }

    public static Rating newRating(
            RatingOptions overallRating,
            RatingOptions supportResponseTimeRating,
            RatingOptions afterSalesServiceRating
    ) {
        return new Rating(
                overallRating,
                supportResponseTimeRating,
                afterSalesServiceRating
        );
    }

    @Override
    protected void selfValidate() {
        if (this.overallRating == null) {
            this.notification.append(new Error("Necessário ter a avaliação geral"));
        }
    }

    @Override
    public Rating getValue() {
        return this;
    }
}
