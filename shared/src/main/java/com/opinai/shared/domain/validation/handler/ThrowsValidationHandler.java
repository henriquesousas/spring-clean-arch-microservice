package com.opinai.shared.domain.validation.handler;


import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw ValidationException.with(error);
    }

    @Override
    public ValidationHandler append(final DomainException error) {
        throw new RuntimeException(error.getMessage());
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }


    @Override
    public void copy(Notification notification) {
        throw ValidationException.with(new Error(notification.getFirstError().message()));
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

}
