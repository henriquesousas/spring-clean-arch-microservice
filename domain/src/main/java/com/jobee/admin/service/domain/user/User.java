package com.jobee.admin.service.domain.user;

import com.jobee.admin.service.domain.AggregateRoot;
import com.jobee.admin.service.domain.user.valueobjects.*;
import com.opinai.shared.domain.utils.InstantUtils;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
public class User extends AggregateRoot<UserId> {
    private String name;
    private Email email;
    private Phone phone;
    private Password password;
    private boolean isActive;
    private Set<Role> roles;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private User(
            final UserId userId,
            final String name,
            final Email email,
            final Phone phone,
            final Password password,
            final boolean isActive,
            final Set<Role> roles,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(userId);
        this.name = Objects.requireNonNull(name);
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.deletedAt = deletedAt;
        validate(notification);
    }

    public static User newUser(UserBuilder builder) {
        return new User(
                builder.getUserId(),
                builder.getName(),
                Email.from(builder.getEmail()),
                Phone.from(builder.getPhone()),
                Password.from(builder.getPassword()),
                builder.isActive(),
                builder.getRoles().stream()
                        .map(Role::from)
                        .collect(Collectors.toSet()),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt());
    }

    //TODO: Implement the validate method
    @Override
    public void validate(ValidationHandler handler) {

    }

    //TODO: Implement the update method
    //TODO: Implement deactivate method
    //TODO: Implement active method


}
