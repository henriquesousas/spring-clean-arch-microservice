package com.opinai.review.domain.exceptions;


import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class ReviewNullableParamException extends DomainException {
    public ReviewNullableParamException() {
        super("BadRequest", List.of(new Error("Por favor infome pelo menos um parâmetro para realizar a consulta.")));
    }

    @Override
    public int getStatus() {
        return 400;
    }
}
