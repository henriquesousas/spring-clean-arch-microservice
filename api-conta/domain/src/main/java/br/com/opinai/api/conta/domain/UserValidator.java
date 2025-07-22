package br.com.opinai.api.conta.domain;

import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;
import com.opinai.shared.domain.validation.Validator;

import java.util.stream.Stream;

public class UserValidator extends Validator {

    private final User user;

    protected UserValidator(final User user, final ValidationHandler handler) {
        super(handler);
        this.user = user;
    }

    @Override
    public void validate() {
        firstNameConstraint(this.user.getFirstName());
        lastNameConstraint(this.user.getLastName());
        valueObjectsConstraint();
    }

    private void lastNameConstraint(String lastName) {
        if (lastName== null || lastName.trim().isEmpty()) {
            this.validationHandler().append(new Error("'%s' não deve ser nulo ou vazio".formatted("Último nome")));
        }

        if (lastName != null) {
            final int length = lastName.trim().length();
            if (length < 3 || length > 255) {
                this.validationHandler().append(new Error("'%s' deve ter entre 3 e 255 caracteres".formatted("Último nome")));
            }
        }
    }

    private void firstNameConstraint(String firstName) {
        if (firstName== null || firstName.trim().isEmpty()) {
            this.validationHandler().append(new Error("'%s' não deve ser nulo ou vazio".formatted("Nome")));
        }

        if (firstName != null) {
            final int length = firstName.trim().length();
            if (length < 3 || length > 255) {
                this.validationHandler().append(new Error("'%s' deve ter entre 3 e 255 caracteres".formatted("Nome")));
            }
        }
    }

    private void valueObjectsConstraint() {
        Stream.of(
                this.user.getId().getNotification(),
                this.user.getBirthdate().getNotification(),
                this.user.getEmail().getNotification(),
                this.user.getPassword().getNotification(),
                this.user.getPhone().getNotification()
        ).forEach(this::copyIfHasError);
    }
}
