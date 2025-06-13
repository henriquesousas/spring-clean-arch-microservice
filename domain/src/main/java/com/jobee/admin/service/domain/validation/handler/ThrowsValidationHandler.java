package com.jobee.admin.service.domain.validation.handler;

import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw new  RuntimeException(error.message());
    }

    @Override
    public ValidationHandler append(final DomainException error) {
        throw new  RuntimeException(error.getMessage());
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }


    @Override
    public void copy(Notification notification) {
//        throw DomainException.with( new Error(notification.getFirstError().message()));
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

}
