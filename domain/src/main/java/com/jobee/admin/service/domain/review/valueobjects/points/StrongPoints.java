package com.jobee.admin.service.domain.review.valueobjects.points;

import java.util.HashSet;
import java.util.Set;

public class StrongPoints extends Points {

    private StrongPoints(Set<String> points) {
        super(points);
    }

    public static StrongPoints from() {
        return new StrongPoints(new HashSet<>());
    }

    public static StrongPoints from(Set<String> points) {
        return new StrongPoints(points);
    }

    @Override
    protected String getMaxSizeExceededMessage() {
        return "Pontos forte execedeu o limite";
    }
}
