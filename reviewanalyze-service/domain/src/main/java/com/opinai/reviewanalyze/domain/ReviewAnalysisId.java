package com.opinai.reviewanalyze.domain;

import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.utils.IdUtils;

public class ReviewAnalysisId extends Identifier {

    public ReviewAnalysisId(String value) {
        super(value);
    }

    public static ReviewAnalysisId unique() {
        return new ReviewAnalysisId(IdUtils.uuid());
    }

    public static ReviewAnalysisId from(String value) {
        return new ReviewAnalysisId(value);
    }
}