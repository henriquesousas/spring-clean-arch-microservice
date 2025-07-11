package com.opinai.review.infrastructure.exceptions;

import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.validation.Error;
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

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(final Exception ex) {
        return ResponseEntity.status(500).body( ApiError.internalServerError(ex.getMessage()));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> handleBadException(final Exception ex) {
        return ResponseEntity.status(400).body( ApiError.badRequestError(ex.getMessage()));
    }

    record ApiError(String message, List<String> errors) {

        static ApiError from(DomainException ex) {
            final var errors = ex.getErrors().stream()
                    .map(Error::message)
                    .toList();

            return new ApiError(ex.getMessage(), errors);
        }

        static ApiError internalServerError(String ex) {
            return new ApiError("InternalServerError", List.of(ex));
        }

        static ApiError badRequestError(String ex) {
            return new ApiError("BadRequestError", List.of(ex));
        }
    }
}
