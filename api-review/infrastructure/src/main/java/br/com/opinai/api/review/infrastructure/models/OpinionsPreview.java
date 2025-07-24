package br.com.opinai.api.review.infrastructure.models;

import br.com.opinai.api.review.domain.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.opinai.api.review.domain.valueobjects.Feedback;
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
