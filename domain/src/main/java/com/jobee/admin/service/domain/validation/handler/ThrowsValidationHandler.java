package com.jobee.admin.service.domain.validation.handler;

import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with(error);
    }

    @Override
    public ValidationHandler append(DomainException error) {
        throw DomainException.with(new Error(error.getMessage()));
    }

    //    @Override
//    public ValidationHandler append(final ValidationHandler handler) {
//        throw DomainException.with(handler.getErrors());
//    }
//
//    @Override
//    public ValidationHandler validate(final Validation validation) {
//        try {
//            validation.validate();
//        } catch (Exception e) {
//            throw DomainException.with(new Error(e.getMessage()));
//        }
//        return this;
//    }
//
    @Override
    public List<Error> getErrors() {
        return List.of();
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

    @Override
    public Error firstError() {
        return ValidationHandler.super.firstError();
    }
}
