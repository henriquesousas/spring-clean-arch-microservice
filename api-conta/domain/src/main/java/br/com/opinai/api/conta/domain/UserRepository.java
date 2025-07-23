package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.UserId;

import java.util.Optional;

public interface UserRepository {
    void create(User  user);
    User update(User user);
    User getById(UserId id);
    Optional<User> getByEmail(String email);
}
