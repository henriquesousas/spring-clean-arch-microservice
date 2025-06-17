package com.jobee.admin.service.domain.analysis;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.review.valueobjects.ReviewId;
import com.jobee.admin.service.domain.utils.IdUtils;

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