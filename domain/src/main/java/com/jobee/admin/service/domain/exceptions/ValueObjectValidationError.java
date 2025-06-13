package com.jobee.admin.service.domain.commons.exceptions;

import com.jobee.admin.service.domain.commons.validation.Error;

import java.util.List;

//TODO: Remover
public class ValueObjectValidationError extends DomainException{
    protected ValueObjectValidationError(String message, List<Error> errors) {
        super(message, errors);
    }
}
