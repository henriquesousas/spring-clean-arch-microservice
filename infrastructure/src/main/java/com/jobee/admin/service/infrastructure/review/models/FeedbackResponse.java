package com.jobee.admin.service.infrastructure.review.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobee.admin.service.domain.review.valueobjects.Feedback;
import com.jobee.admin.service.domain.utils.CollectionUtils;

import java.util.Set;

public record FeedbackResponse(
        @JsonProperty("positive")  Set<String> positiveFeedback,
        @JsonProperty("negative") Set<String> negativeFeedback
) {
    public static FeedbackResponse with(Set<Feedback> positiveFeedback, Set<Feedback> negativeFeedback) {
        return new FeedbackResponse(
                CollectionUtils.asSet(positiveFeedback, Feedback::getValue),
                CollectionUtils.asSet(negativeFeedback, Feedback::getValue)
        );
    }
}
