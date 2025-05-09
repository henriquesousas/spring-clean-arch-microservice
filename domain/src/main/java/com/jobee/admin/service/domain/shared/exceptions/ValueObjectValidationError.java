package com.jobee.admin.service.domain.shared.exceptions;

import com.jobee.admin.service.domain.shared.validation.Error;

import java.util.List;

//TODO: Remover
public class ValueObjectValidationError extends DomainException{
    protected ValueObjectValidationError(String message, List<Error> errors) {
        super(message, errors);
    }
}
