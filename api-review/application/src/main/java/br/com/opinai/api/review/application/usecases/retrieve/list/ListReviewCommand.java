package br.com.opinai.api.review.application.usecases.retrieve.list;

public record ListReviewCommand(
        String status,
        String userId,
        String productId
){

}
