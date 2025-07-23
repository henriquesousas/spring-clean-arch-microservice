package br.com.opinai.api.conta.domain.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record CreateUserRequest(
        @JsonProperty("firstname") String firstName,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("email") String email,
        @JsonProperty("gender") String gender,
        @JsonProperty("birthdate") String birthdate,
        @JsonProperty("password") String password,
        @JsonProperty("phone") String phone,
        @JsonProperty("roles") Set<String> roles
) {
}
