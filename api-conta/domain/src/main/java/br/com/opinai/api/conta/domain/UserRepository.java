package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.UserId;

import java.util.Optional;

public interface UserRepository {
    void create(User  user);
    User update(User user);
    Optional<User> getById(UserId id);
}
