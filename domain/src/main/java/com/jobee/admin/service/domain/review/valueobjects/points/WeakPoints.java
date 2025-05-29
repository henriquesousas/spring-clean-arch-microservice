package com.jobee.admin.service.domain.review.valueobjects.points;

import java.util.HashSet;
import java.util.Set;

public class WeakPoints extends Points {

    private WeakPoints(Set<String> points) {
        super(points);
    }

    public static WeakPoints from() {
        return new WeakPoints(new HashSet<>());
    }

    public static WeakPoints from(Set<String> points) {
        return new WeakPoints(points);
    }

      @Override
    protected String getMaxSizeExceededMessage() {
        return "Pontos fraco execedeu o limite";
    }
}
