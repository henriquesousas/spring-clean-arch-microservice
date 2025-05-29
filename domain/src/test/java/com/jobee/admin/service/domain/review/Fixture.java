package com.jobee.admin.service.domain.review;

import com.github.javafaker.Faker;
import com.jobee.admin.service.domain.review.enums.Type;
import com.jobee.admin.service.domain.review.enums.RatingScale;
import com.jobee.admin.service.domain.user.valueobjects.UserId;


public class Fixture {

    private static final Faker FAKER = new Faker();

    public static String lorem() {
        return FAKER.lorem().fixedString(10);
    }

    public static String summary() {
        return FAKER.lorem().fixedString(10);
    }

    public static RatingScale rating() {
        return FAKER.options().option(RatingScale.values());
    }

    public static UserId userId() {
        return UserId.unique();
    }

    public static Type productType() {
        return FAKER.options().option(Type.PRODUCT, Type.SERVICE);
    }

    public static RatingScale afterSaleServiceRating() {
        return FAKER.options().option(RatingScale.values());
    }

    public static RatingScale supportTimeResponseRating() {
        return FAKER.options().option(RatingScale.values());
    }
//
//    public static Review review() {
//        return new ReviewBuilder(
//                lorem(),
//                summary(),
//                userId(),
//                productType(),
//                lorem(), Ratings.newRating(RatingOptions.RA_1)).build();
//    }
}
