package br.com.opinai.api.conta.domain.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserRequest(
        @JsonProperty("firstname") String firstName,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("email") String email,
        @JsonProperty("gender") String gender,
        @JsonProperty("birthdate") String birthdate
) {
}
