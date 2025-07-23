package br.com.opinai.api.conta.application.usecase.update;

import br.com.opinai.api.conta.domain.UserRepository;
import br.com.opinai.api.conta.domain.exceptions.UserNotFoundException;
import br.com.opinai.api.conta.domain.valueobjects.UserId;
import com.opinai.shared.application.Unit;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import com.opinai.shared.domain.exceptions.ValidationException;
import io.vavr.control.Either;

import java.util.Objects;

public class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<DomainException, Unit>> {

    private final UserRepository repository;

    public UpdateUserUseCase(final UserRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, Unit> execute(UpdateUserCommand command) {

        final var user = this.repository.getById(UserId.from(command.userId()));

        if (user == null) {
            return Either.left(new UserNotFoundException());
        }

        if (user.getFirstName() != null) user.changeFirstName(command.firstName());
        if (user.getLastName() != null) user.changeLastName(command.lastName());
        if (user.getBirthdate() != null) user.changeBirthdate(command.birthdate());
        if (user.getGender() != null) user.changeGender(command.gender());
//        if (user.getEmail() != null) user.changeEmail(command.email());

        if (user.getNotification().hasError()) {
            final var errors = user.getNotification().getErrors();
            return Either.left(ValidationException.with(errors));
        }

        this.repository.update(user);
        return Either.right(new Unit());
    }
}
