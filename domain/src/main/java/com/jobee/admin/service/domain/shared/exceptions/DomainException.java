package com.jobee.admin.service.domain.shared.exceptions;

import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException {

    private final List<Error> errors;

    protected DomainException(final String message, final List<Error> errors) {
        super(message, null, true, false);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public static DomainException with(Error error) {
        return new DomainException(error.message(), List.of(error));
    }

    public int getStatus() {
        return 422;
    }
}
