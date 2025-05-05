package com.jobee.admin.service.domain.exceptions;

import com.jobee.admin.service.domain.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException {
    private final List<Error> errors;

    protected DomainException(final String message, final List<Error> errors) {
        super(message, null, true, false);
        this.errors = errors;
    }

    public static DomainException with( Error error) {
        return new DomainException(error.message(),List.of(error));
    }


    public static DomainException with(final List<Error> errors) {
        return new DomainException("",errors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
