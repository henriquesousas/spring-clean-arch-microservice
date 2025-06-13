package com.jobee.admin.service.domain.commons.validation.handler;

import com.jobee.admin.service.domain.commons.exceptions.DomainException;
import com.jobee.admin.service.domain.commons.validation.Error;
import com.jobee.admin.service.domain.commons.validation.ValidationHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO: Change List to Hashset
public class Notification implements ValidationHandler {
    private  Set<Error> errors;

    private Notification() {
        this.errors = new HashSet<>();
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
        return errors.stream().toList();
    }
}
