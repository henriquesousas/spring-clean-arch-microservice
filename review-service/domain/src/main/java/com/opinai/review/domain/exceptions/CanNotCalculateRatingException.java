package com.opinai.review.domain.exceptions;


import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class CanNotCalculateRatingException extends NotFoundException {
    public CanNotCalculateRatingException() {
        super("NotFound", List.of(new Error("Não foi possível calcular a média.")));
    }
}
