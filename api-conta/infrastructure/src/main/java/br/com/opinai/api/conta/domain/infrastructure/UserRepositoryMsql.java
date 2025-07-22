package br.com.opinai.api.conta.domain.infrastructure;

import br.com.opinai.api.conta.domain.User;
import br.com.opinai.api.conta.domain.UserRepository;
import br.com.opinai.api.conta.domain.valueobjects.UserId;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserRepositoryMsql implements UserRepository {

    private final UserRepositoryJpa repository;

    public UserRepositoryMsql(final UserRepositoryJpa repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void create(User user) {
        this.repository.save(UserJpaEntity.from(user));
    }

    @Override
    public User update(User user) {
        final var model = this.repository.save(UserJpaEntity.from(user));
        return model.toAggregate();
    }

    @Override
    public Optional<User> getById(UserId id) {
        return this.repository.findById(id.getValue())
                .map(UserJpaEntity::toAggregate);
    }
}
