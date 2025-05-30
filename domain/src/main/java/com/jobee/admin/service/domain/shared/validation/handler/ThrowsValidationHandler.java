package com.jobee.admin.service.domain.shared.validation.handler;

import com.jobee.admin.service.domain.shared.exceptions.DomainException;
import com.jobee.admin.service.domain.shared.validation.Error;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;

import java.util.List;
import java.util.Set;

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

//    @Override
//    public List<Error> getErrors() {
//        return List.of();
//    }

    @Override
    public void copy(Notification notification) {
//        throw DomainException.with( new Error(notification.getFirstError().message()));
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

//    @Override
//    public Error getFirstError() {
//        return ValidationHandler.super.getFirstError();
//    }
}
