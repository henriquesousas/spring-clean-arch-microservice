package com.opinai.review.infrastructure.models;

import com.opinai.review.domain.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opinai.review.domain.valueobjects.Feedback;
import com.opinai.shared.domain.utils.CollectionUtils;

import java.util.Set;

public record OpinionsPreview(
        @JsonProperty("pros") Set<String> pros,
        @JsonProperty("cons")  Set<String> cons
) {

    public static OpinionsPreview from(Review review) {
        return new OpinionsPreview(
                CollectionUtils.asSet(review.getPros(), Feedback::getValue),
                CollectionUtils.asSet(review.getCons(), Feedback::getValue)
        );
    }
}
