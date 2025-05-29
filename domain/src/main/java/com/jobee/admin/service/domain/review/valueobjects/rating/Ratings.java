package com.jobee.admin.service.domain.review.valueobjects.rating;

import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.shared.ValueObject;

import java.util.Optional;

public class Ratings extends ValueObject<Ratings> {
    private final OverallRating overallRating;
    private final SupportRating supportRating;
    private final AfterSalesRating afterSalesRating;

    private Ratings(OverallRating overallRating, SupportRating supportRating, AfterSalesRating afterSalesRating) {
        super();
        this.overallRating = overallRating;
        this.supportRating = supportRating;
        this.afterSalesRating = afterSalesRating;
    }


    public static Ratings from(
            OverallRating overallRating,
            SupportRating supportResponseTimeRating,
            AfterSalesRating afterSalesServiceRating
    ) {
        return new Ratings(overallRating, supportResponseTimeRating, afterSalesServiceRating);
    }

    public static Ratings overall(RatingScale scale) {
        return Ratings.from(OverallRating.from(scale), null, null);
    }

    public Integer overallRating() {
        return this.overallRating.getValue();
    }

    public Integer supportRating() {
        return supportRating != null ? supportRating.getValue() : null;
    }

    public Integer afterSalesRating() {
        return afterSalesRating != null ? afterSalesRating.getValue() : null;
    }

    @Override
    public Ratings getValue() {
        return this;
    }
}
