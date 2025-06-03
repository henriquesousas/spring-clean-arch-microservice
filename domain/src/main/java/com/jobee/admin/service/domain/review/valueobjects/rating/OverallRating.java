//package com.jobee.admin.service.domain.review.valueobjects.rating;
//
//import com.jobee.admin.service.domain.review.enums.RatingScale;
//import com.jobee.admin.service.domain.ValueObject;
//import com.jobee.admin.service.domain.validation.Error;
//
//public class OverallRating extends ValueObject<Integer> {
//
//    private final RatingScale scale;
//
//    private OverallRating(RatingScale scale) {
//        this.scale = scale;
//        selfValidate();
//    }
//
//    public static OverallRating from(RatingScale scale) {
//        return new OverallRating(scale);
//    }
//
//
//    private void selfValidate() {
//        if (this.scale == null) {
//            this.notification.append(new Error("Avaliação geral é obrigatória"));
//        }
//    }
//
//    @Override
//    public Integer getValue() {
//        return this.scale.getValue();
//    }
//}
