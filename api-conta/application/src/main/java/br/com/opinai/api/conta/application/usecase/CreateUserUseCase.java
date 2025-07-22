package br.com.opinai.api.conta.application.usecase;

import br.com.opinai.api.conta.domain.Gender;
import br.com.opinai.api.conta.domain.UserBuilder;
import br.com.opinai.api.conta.domain.UserRepository;
import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import com.opinai.shared.domain.utils.EnumUtils;
import io.vavr.control.Either;

public class CreateUserUseCase extends UseCase<CreateUserCommand, Either<DomainException, UserOutput>> {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, UserOutput> execute(CreateUserCommand command) {

        final var user = UserBuilder.create(
                command.firstName(),
                command.lastName(),
                command.email(),
                EnumUtils.of(Gender.values(), command.gender()),
                BirthDate.from(command.birthDate()),
                command.password(),
                command.phone(),
                command.roles()
        ).build();

        if (user.getNotification().hasError()) {
            final var errors = user.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        this.repository.create(user);

        return Either.right(UserOutput.from(user));
    }
}
