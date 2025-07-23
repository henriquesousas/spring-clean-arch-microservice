package com.opinai.shared.domain.exceptions;


import com.opinai.shared.domain.validation.Error;

import java.util.List;

public  class BadRequestException extends DomainException {

    public BadRequestException(List<Error> errors) {
        super("BADREQUEST", errors);
    }

    @Override
    public int getStatus() {
        return 400;
    }
}
