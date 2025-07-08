package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.CollectionUtils;

import java.util.Set;

// TODO: Remove
public record FeedbackResponse(
        @JsonProperty("pros")  Set<String> pros,
        @JsonProperty("cons") Set<String>  cons
) {
    public static FeedbackResponse with(Set<Feedback> pros, Set<Feedback> cons) {
        return new FeedbackResponse(
                CollectionUtils.asSet(pros, Feedback::getValue),
                CollectionUtils.asSet(cons, Feedback::getValue)
        );
    }
}
