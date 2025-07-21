package br.com.opinai.api.gestao.produto.infrastructure.core.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(final String message) {
        super(message);
    }

}