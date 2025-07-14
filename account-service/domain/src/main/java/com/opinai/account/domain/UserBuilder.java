package com.opinai.account.domain;

import com.opinai.account.domain.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;

@Getter
public class UserBuilder {
    private UserId userId = UserId.unique();
    private final String name;
    private final String email;
    private final String password;
    private final String phone;
    private final Set<String> roles;
    private boolean isActive = true;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public UserBuilder(String name, String email, String password, String phone, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
    }

    public UserBuilder withUserId(String userId) {
        this.userId = UserId.from(userId);
        return this;
    }

    public UserBuilder withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public UserBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public UserBuilder withDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public User build() {
        return User.newUser(this);
    }
}
