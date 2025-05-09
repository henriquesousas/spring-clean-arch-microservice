package com.jobee.admin.service.domain.shared.exceptions;

import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.List;

public final class ValidationException extends DomainException {

    private ValidationException(List<Error> errors) {
        super("UnprocessableEntity", errors);
    }

    public static ValidationException with(List<Error> errors) {
        return new ValidationException(errors);
    }
}
