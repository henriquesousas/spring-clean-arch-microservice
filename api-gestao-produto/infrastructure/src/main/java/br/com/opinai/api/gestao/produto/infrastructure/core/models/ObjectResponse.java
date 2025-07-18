package br.com.opinai.api.gestao.produto.infrastructure.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectResponse<T> extends ApiResponse<T> {
    @JsonProperty("data")
    private T data;

    public ObjectResponse(T data) {
        this.data = data;
    }

    public static <T> ObjectResponse<T> from(T data) {
        return new ObjectResponse<>(data);
    }

}

