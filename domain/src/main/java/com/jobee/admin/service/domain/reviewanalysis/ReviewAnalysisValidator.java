package com.jobee.admin.service.domain.reviewanalysis;

import com.jobee.admin.service.domain.utils.IdUtils;
import com.jobee.admin.service.domain.utils.NullableUtils;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import com.jobee.admin.service.domain.validation.Validator;
import com.jobee.admin.service.domain.validation.handler.Notification;

import java.util.stream.Stream;

public class ReviewAnalysisValidator extends Validator {

    private final ReviewAnalysis reviewAnalysis;
    private final ValidationHandler handler;

    protected ReviewAnalysisValidator(final ReviewAnalysis reviewAnalysis, final ValidationHandler handler) {
        super(handler);
        this.reviewAnalysis = reviewAnalysis;
        this.handler = handler;
    }

    @Override
    public void validate() {
        validateValueObjects();
        validaId(this.reviewAnalysis.getReviewId(), "ReviewId must be a valid UUID");
        validaId(this.reviewAnalysis.getModeratorId(), "ModeratorId must be a valid UUID");
        validaId(this.reviewAnalysis.getUserId(), "UserId must be a valid UUID");
    }

    private void validateValueObjects() {
        Stream.of(
                this.reviewAnalysis.getId().getNotification(),
                NullableUtils.mapOrNull(this.reviewAnalysis.getReason(), Reason::getNotification)
        ).forEach(this::copyIfHasError);
    }

    private void validaId(final String uuid, final String message) {
        if(uuid == null) return;
        if (!IdUtils.isValid(uuid)) {
            this.reviewAnalysis.getNotification().append(new Error(message));
        }
    }

    private void copyIfHasError(final Notification notification) {
        if (notification == null) return;
        if (notification.hasError()) {
            this.handler.copy(notification);
        }
    }
}
