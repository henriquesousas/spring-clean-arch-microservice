package com.opinai.shared.domain.validation.handler;

import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Notification implements ValidationHandler {
    private final Set<Error> errors;

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
        this.errors.addAll(error.getErrors());
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
