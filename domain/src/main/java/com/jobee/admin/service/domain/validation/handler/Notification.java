package com.jobee.admin.service.domain.validation.handler;

import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {
    private final List<Error> errors;

    private Notification() {
        this.errors = new ArrayList<>();
    }

    public static Notification create() {
        return new Notification();
    }

    public static Notification create(Throwable error) {
        return new Notification().append(new Error(error.getMessage()));
    }

    @Override
    public Notification append(Error error) {
        this.errors.add(error);
        return this;
    }

    @Override
    public Notification append(DomainException error) {
        this.errors.add(new Error(error.getMessage()));
        return this;
    }

    @Override
    public void copy(Notification notification) {
        notification.errors.forEach(this::append);
    }

    @Override
    public List<Error> getErrors() {
        return errors;
    }
}
