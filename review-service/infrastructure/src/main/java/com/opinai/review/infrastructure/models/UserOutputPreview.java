package com.opinai.review.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opinai.review.domain.Review;

public record UserOutputPreview(
       @JsonProperty("userId")  String userId,
       @JsonProperty("photoUrl")  String photoUrl,
       @JsonProperty("name")  String name
) {
    public static UserOutputPreview from(Review review) {
        return new UserOutputPreview(review.getUser().userId(), review.getUser().name(), review.getUser().photoUrl() );
    }

}
