package com.jobee.admin.service.domain.exceptions;

import com.jobee.admin.service.domain.validation.Error;

import java.util.List;

//TODO: Remover
public class ValueObjectValidationError extends DomainException{
    protected ValueObjectValidationError(String message, List<Error> errors) {
        super(message, errors);
    }
}
