package com.jobee.admin.service.domain.validation.handler;

import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with( error);
    }

    @Override
    public ValidationHandler append(DomainException error) {
        throw DomainException.with( new Error(error.getMessage()));
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

    @Override
    public void copy(Notification notification) {
        throw DomainException.with( new Error(notification.getFirstError().message()));
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

    @Override
    public Error getFirstError() {
        return ValidationHandler.super.getFirstError();
    }
}
