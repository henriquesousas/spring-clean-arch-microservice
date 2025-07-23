package br.com.opinai.api.conta.application.usecase.update;

public record UpdateUserCommand(
        String userId,
        String firstName,
        String lastName,
        String email,
        String birthdate,
        String gender
) {
}
