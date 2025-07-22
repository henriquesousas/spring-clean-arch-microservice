package br.com.opinai.api.conta.application.usecase;

import br.com.opinai.api.conta.domain.User;

public record UserOutput(
        String firstName,
        String lastName,
        String email,
        String gender,
        String birthDate,
        String phone
) {

    public static UserOutput from(User user) {
        return new UserOutput(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().getValue(),
                user.getGender().getValue(),
                user.getBirthdate().getValue(),
                user.getPhone().getValue());
    }
}
