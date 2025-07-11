package com.opinai.review.domain.exceptions;


import com.opinai.shared.domain.exceptions.NotFoundException;
import com.opinai.shared.domain.validation.Error;

import java.util.List;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException() {
        super("NotFound", List.of(new Error("Review não encontrado.")));
    }
}
