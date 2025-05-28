package com.jobee.admin.service.domain.review.valueobjects.rating;

import com.jobee.admin.service.domain.shared.ValueObject;



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


    public static Ratings newRating(
            OverallRating overallRating,
            SupportRating supportResponseTimeRating,
            AfterSalesRating afterSalesServiceRating
    ) {
        return new Ratings(overallRating, supportResponseTimeRating, afterSalesServiceRating);
    }

    public OverallRating overallRating() {
        return this.overallRating;
    }

    public SupportRating supportRating() {
        return this.supportRating;
    }

    public AfterSalesRating afterSalesRating() {
        return this.afterSalesRating;
    }

    @Override
    public Ratings getValue() {
        return this;
    }
}
