package com.jobee.admin.service.application.usecases.review.retrieve.list;

public record ListReviewCommand(
        String status,
        String userId,
        String productId
){

}
