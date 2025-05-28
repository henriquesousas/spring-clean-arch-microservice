package com.jobee.admin.service.domain.review.valueobjects.points;

import java.util.Set;

public class WeakPoints extends Points {

    private WeakPoints(Set<String> points) {
        super(points);
    }

    public static WeakPoints of(Set<String> points) {
        return new WeakPoints(points);
    }

      @Override
    protected String getMaxSizeExceededMessage() {
        return "Pontos fraco execedeu o limite";
    }
}
