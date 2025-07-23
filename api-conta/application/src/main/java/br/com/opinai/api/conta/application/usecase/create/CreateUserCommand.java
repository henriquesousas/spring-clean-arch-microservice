package br.com.opinai.api.conta.application.usecase.create;

import java.util.Set;

public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String gender,
        String birthDate,
        String password,
        String phone,
        Set<String> roles
) {
}
