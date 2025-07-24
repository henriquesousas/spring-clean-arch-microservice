package br.com.opinai.api.review.application.usecases.summary;

import br.com.opinai.api.review.application.usecases.RatingSummaryOutputCommand;
import br.com.opinai.api.review.domain.Review;

import java.util.List;

public record ReviewSummaryOutputCommand(
        RatingSummaryOutputCommand ratingSummary,
        List<Review> reviews
) {
}
