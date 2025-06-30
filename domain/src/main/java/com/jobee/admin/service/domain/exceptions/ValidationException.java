package com.jobee.admin.service.domain.exceptions;


import com.jobee.admin.service.domain.validation.Error;

import java.util.List;

public final class ValidationException extends DomainException {

    private ValidationException(List<Error> errors) {
        super("UnprocessableEntity", errors);
    }

    public static ValidationException with(List<Error> errors) {
        return new ValidationException(errors);
    }

    public static ValidationException with(Error error) {
        return new ValidationException(List.of(error));
    }

    @Override
    public int getStatus() {
        return 422;
    }
}
