package br.com.opinai.api.conta.domain.infrastructure.models;

import br.com.opinai.api.conta.domain.Role;
import br.com.opinai.api.conta.domain.User;
import com.opinai.shared.domain.utils.EnumUtils;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String password,
        String email,
        String gender,
        String birthDate,
        String phone,
        Set<String> roles,
        Instant createdAt
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getAggregateId().getValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword().getValue(),
                user.getEmail().getValue(),
                user.getGender().getValue(),
                user.getBirthdate().getValue(),
                user.getPhone().getValue(),
                user.getRoles().stream().map(role -> EnumUtils.of(Role.values(), role.getValue()).getValue()).collect(Collectors.toSet()),
                user.getCreatedAt()
        );
    }
}
