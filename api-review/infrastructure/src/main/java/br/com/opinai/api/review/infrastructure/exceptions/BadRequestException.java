package br.com.opinai.api.review.infrastructure.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String message) {
        super(message);
    }

}