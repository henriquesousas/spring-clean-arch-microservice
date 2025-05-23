package com.jobee.admin.service.domain.review;

import com.github.javafaker.Faker;
import com.jobee.admin.service.domain.review.enums.ProductType;
import com.jobee.admin.service.domain.review.enums.RatingOptions;
import com.jobee.admin.service.domain.user.valueobjects.UserId;


public class Fixture {

    private static final Faker FAKER = new Faker();

    public static String lorem() {
        return FAKER.lorem().fixedString(10);
    }

    public static String summary() {
        return FAKER.lorem().fixedString(10);
    }

    public static RatingOptions rating() {
        return FAKER.options().option(RatingOptions.values());
    }

    public static UserId userId() {
        return UserId.unique();
    }

    public static ProductType productType() {
        return FAKER.options().option(ProductType.PRODUCT, ProductType.SERVICE);
    }

    public static RatingOptions afterSaleServiceRating() {
        return FAKER.options().option(RatingOptions.values());
    }

    public static RatingOptions supportTimeResponseRating() {
        return FAKER.options().option(RatingOptions.values());
    }

    public static Review review() {
        return new ReviewBuilder(
                lorem(),
                summary(),
                userId(),
                productType(),
                lorem(), Rating.newRating(RatingOptions.RA_1)).build();
    }
}
