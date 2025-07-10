package com.jobee.admin.service.domain.review.exceptions;

import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.Error;

import java.util.List;

public class CanNotCalculateRatingException extends NotFoundException {
    public CanNotCalculateRatingException() {
        super("NotFound", List.of(new Error("Não foi possível calcular a média.")));
    }
}
