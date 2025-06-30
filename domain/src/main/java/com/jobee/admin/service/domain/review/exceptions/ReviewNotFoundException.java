package com.jobee.admin.service.domain.review.exceptions;

import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.validation.Error;

import java.util.List;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException() {
        super("NotFound", List.of(new Error("Review não encontrado.")));
    }
}
