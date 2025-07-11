package com.opinai.shared.domain.exceptions;

import com.opinai.shared.domain.validation.Error;
import lombok.Getter;

import java.util.List;

@Getter
public  abstract class DomainException extends RuntimeException {

    public final List<Error> errors;

    protected DomainException(final String message, final List<Error> errors) {
        super(message, null, true, false);
        this.errors = errors;
    }

    public abstract int getStatus();

}
