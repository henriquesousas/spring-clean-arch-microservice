package com.opinai.shared.domain.exceptions;

import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class ApplicationException extends DomainException {

    protected ApplicationException(final String message, Error error) {
        super(message, List.of(error));
    }

    public static ApplicationException with(Throwable error) {
        return new ApplicationException("Unexpected error", new Error(error.getMessage()));
    }

    @Override
    public int getStatus() {
        return 500;
    }
}
