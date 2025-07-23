package br.com.opinai.api.conta.application.usecase.getByEmail;

import br.com.opinai.api.conta.domain.*;
import br.com.opinai.api.conta.domain.exceptions.UserNotFoundException;
import br.com.opinai.api.conta.domain.valueobjects.UserId;
import com.opinai.shared.application.UseCase;
import com.opinai.shared.domain.exceptions.DomainException;
import io.vavr.control.Either;

public class GetUserByIdUseCase extends UseCase<String, Either<DomainException, User>> {

    private final UserRepository repository;

    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<DomainException, User> execute(String id) {
        final var user = this.repository.getById(UserId.from(id));
        return user != null
                ? Either.right(user)
                : Either.left(new UserNotFoundException());

    }
}
