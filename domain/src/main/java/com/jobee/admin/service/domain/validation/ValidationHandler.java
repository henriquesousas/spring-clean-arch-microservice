package com.jobee.admin.service.domain.validation;


import com.jobee.admin.service.domain.exceptions.DomainException;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(final Error error);
    ValidationHandler append(final DomainException error);
    List<Error> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        if(getErrors() != null && !getErrors().isEmpty()) {
            return  getErrors().get(0);
        }
        return null;
    }

//    ValidationHandler append(final ValidationHandler handler);
//
//    ValidationHandler validate(Validation validation);

//    interface Validation {
//        void validate();
//    }
}
