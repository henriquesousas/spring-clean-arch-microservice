package com.jobee.admin.service.domain.exceptions;

import com.jobee.admin.service.domain.validation.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class DomainException extends RuntimeException {

    public final List<Error> errors;

    protected DomainException(final String message, final List<Error> errors) {
        super(message, null, true, false);
        this.errors = errors;
    }

    // TODO: Remover
    public int getStatus() {
        return 422;
    }
}
