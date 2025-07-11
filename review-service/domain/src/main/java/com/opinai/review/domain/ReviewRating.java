package com.opinai.review.domain;

public record ReviewRating(
        Long totalReviews,
        Long rating1,
        Long rating2,
        Long rating3,
        Long rating4,
        Long rating5
) {}
