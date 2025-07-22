package br.com.opinai.api.conta.application.usecase;

import br.com.opinai.api.conta.domain.Role;

import java.util.Set;

public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String gender,
        String birthDate,
        String password,
        String phone,
        Set<Role> roles
) {
}
