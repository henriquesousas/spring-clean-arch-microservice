package com.jobee.admin.service.infrastructure.exceptions;

import com.jobee.admin.service.domain.exceptions.DomainException;
import com.jobee.admin.service.domain.exceptions.NotFoundException;
import com.jobee.admin.service.domain.exceptions.ValidationException;
import com.jobee.admin.service.domain.validation.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ApiError.from(ex));
    }

    record ApiError(String message, List<String> errors) {

        static ApiError from(DomainException ex) {
            final var errors = ex.getErrors().stream()
                    .map(Error::message)
                    .toList();

            return new ApiError(ex.getMessage(), errors);
        }
    }

}
