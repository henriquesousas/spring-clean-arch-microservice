package com.opinai.review.domain;

import com.opinai.review.domain.valueobjects.Feedback;
import com.opinai.shared.domain.ValueObject;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;
import com.opinai.shared.domain.validation.Validator;
import com.opinai.shared.domain.validation.handler.Notification;

import java.util.Set;
import java.util.stream.Stream;

public class ReviewValidator extends Validator {
    private static final int MAX_LENGTH_ALLOWED_FEEDBACK = 30;
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
        validateTextField(this.review.getComment(), "comentario");
        validateFeedback(this.review.getPros(), "Feedback positivo excedeu o limite permitido");
        validateFeedback(this.review.getCons(), "Feedback negativo excedeu o limite permitido");
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
        Stream.of(
                this.review.getId().getNotification(),
                this.review.getRating().getNotification()
        ).forEach(this::copyIfHasError);


        Stream.concat(this.review.getPros().stream(), this.review.getCons().stream())
                .map(ValueObject::getNotification)
                .forEach(this::copyIfHasError);
    }

    private void validateFeedback(final Set<Feedback> feedbacks, final String message) {
        if (feedbacks.size() > MAX_LENGTH_ALLOWED_FEEDBACK) {
            this.handler.append(new Error(message));
        }
    }

    private void copyIfHasError(final Notification notification) {
        if (notification == null) return;
        if (notification.hasError()) {
            this.handler.copy(notification);
        }
    }
}
