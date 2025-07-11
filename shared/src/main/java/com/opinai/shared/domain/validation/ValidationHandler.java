package com.opinai.shared.domain.validation;

import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.validation.handler.Notification;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(final Error error);

    ValidationHandler append(final DomainException error);

    List<Error> getErrors();

    void copy(Notification notification);

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error getFirstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().stream().toList().get(0);
        }
        return null;
    }
}
