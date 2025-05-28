package com.jobee.admin.service.domain.review;

import com.jobee.admin.service.domain.shared.ValueObject;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;
import com.jobee.admin.service.domain.shared.validation.Validator;
import com.jobee.admin.service.domain.shared.validation.handler.Notification;

import java.util.stream.Stream;

public class ReviewValidator extends Validator {
    private final Review review;
    private final ValidationHandler handler;

    protected ReviewValidator(final Review review, final ValidationHandler handler) {
        super(handler);
        this.review = review;
        this.handler = handler;

    }

    @Override
    public void validate() {
        validateTextField(this.review.getTitle(), "titúlo");
        validateTextField(this.review.getSummary(), "comentario");
        validateValueObjects();
    }

    private void validateTextField(final String value, final String fieldName) {

        if (value == null || value.trim().isEmpty()) {
            this.handler.append(new Error("'%s' não deve ser nulo ou vazio".formatted(fieldName)));
        }

        if (value != null) {
            final int length = value.trim().length();
            if (length < 3 || length > 255) {
                this.handler.append(new Error("'%s' deve ter entre 3 e 255 caracteres".formatted(fieldName)));
            }
        }
    }

    private void validateValueObjects() {
//        Stream.of(
//                this.review.getId().getNotification(),
//                this.review.getUserId().getNotification(),
//                this.review.getNotes().getNotification()
//        ).forEach(this::copyIfHasError);
    }

    private void copyIfHasError(Notification notification) {
        if (notification.hasError()) {
            this.handler.copy(notification);
        }
    }
}
