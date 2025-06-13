package com.jobee.admin.service.domain.validation;


import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.handler.Notification;

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
