package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.domain.review.Review;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.CollectionUtils;

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
