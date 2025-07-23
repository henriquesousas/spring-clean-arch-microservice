package br.com.opinai.api.conta.domain.infrastructure;

import br.com.opinai.api.conta.application.usecase.create.CreateUserCommand;
import br.com.opinai.api.conta.application.usecase.create.CreateUserUseCase;
import br.com.opinai.api.conta.application.usecase.getByEmail.GetUserByIdUseCase;
import br.com.opinai.api.conta.application.usecase.update.UpdateUserCommand;
import br.com.opinai.api.conta.application.usecase.update.UpdateUserUseCase;
import br.com.opinai.api.conta.domain.infrastructure.models.CreateUserRequest;
import br.com.opinai.api.conta.domain.infrastructure.models.UpdateUserRequest;
import br.com.opinai.api.conta.domain.infrastructure.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController

public class UserController implements UserApi {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private GetUserByIdUseCase getUserByIdUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Override
    public ResponseEntity<UserResponse> create(final CreateUserRequest request) {

        final var command = new CreateUserCommand(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.gender(),
                request.birthdate(),
                request.password(),
                request.phone(),
                request.roles()
        );

        return this.createUserUseCase.execute(command)
                .map(user -> {
                    final var response = UserResponse.from(user);
                    return ResponseEntity.created(URI.create("/user/" + response.id())).body(response);
                })
                .getOrElseThrow(err -> err);
    }

    @Override
    public ResponseEntity<UserResponse> getById(String id) {
        return this.getUserByIdUseCase.execute(id)
                .map(user -> ResponseEntity.ok(UserResponse.from(user)))
                .getOrElseThrow(err -> err);
    }

    @Override
    public ResponseEntity<Void> update(String identifier, UpdateUserRequest request) {

        final var command = new UpdateUserCommand(
                identifier,
                request.firstName(),
                request.lastName(),
                request.email(),
                request.birthdate(),
                request.gender()
        );
        ResponseEntity<Void> noContent = ResponseEntity.noContent().build();
        return this.updateUserUseCase.execute(command)
                .map(unit -> noContent)
                .getOrElseThrow(err -> err);
    }
}
