package br.com.opinai.api.conta.application.usecase.create;

import br.com.opinai.api.conta.application.cryptography.Encrypt;
import br.com.opinai.api.conta.domain.*;
import br.com.opinai.api.conta.domain.exceptions.EmailAlreadyExistException;
import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.utils.EnumUtils;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.stream.Collectors;

public class CreateUserUseCase extends UseCase<CreateUserCommand, Either<DomainException, User>> {

    private final UserRepository repository;
    private final Encrypt encrypter;

    public CreateUserUseCase(final UserRepository repository, final Encrypt encrypt) {
        this.repository = Objects.requireNonNull(repository);
        this.encrypter = Objects.requireNonNull(encrypt);
    }

    @Override
    public Either<DomainException, User> execute(CreateUserCommand command) {
        final var user = UserBuilder.create(
                command.firstName(),
                command.lastName(),
                command.email(),
                EnumUtils.of(Gender.values(), command.gender()),
                BirthDate.from(command.birthDate()),
                command.password(),
                command.phone(),
                command.roles().stream().map(s -> EnumUtils.of(Role.values(), s)).collect(Collectors.toSet())
        ).build();

        if (user.getNotification().hasError()) {
            final var errors = user.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        final var userFromDb = this.repository.getByEmail(command.email());

        if (userFromDb.isPresent()) {
            return Either.left(new EmailAlreadyExistException());
        }

        final var passwordEncrypted = this.encrypter.encrypt(command.password());

        System.out.println(passwordEncrypted);

        user.changePassword(passwordEncrypted);

        this.repository.create(user);

        return Either.right(user);
    }
}
