package com.jobee.admin.service.domain.shared.validation;


import com.jobee.admin.service.domain.shared.exceptions.DomainException;
import com.jobee.admin.service.domain.shared.validation.handler.Notification;

import java.util.List;
import java.util.Set;

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
