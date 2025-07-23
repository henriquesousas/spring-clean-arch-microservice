package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import br.com.opinai.api.conta.domain.valueobjects.PhotoUrl;
import br.com.opinai.api.conta.domain.valueobjects.UserId;
import lombok.Getter;

import java.awt.*;
import java.time.Instant;
import java.util.Set;

@Getter
public class UserBuilder {
    private UserId userId = UserId.unique();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Gender gender;
    private final BirthDate birthDate;
    private final String password;
    private final String phone;
    private final Set<Role> roles;
    private boolean isActive = true;
    private PhotoUrl photoUrl;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private UserBuilder(
            String firstName,
            String lastName,
            String email,
            Gender gender,
            BirthDate birthDate,
            String password,
            String phone,
            Set<Role> roles
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
    }

    public static UserBuilder create
            (String firstName,
             String lastName,
             String email,
             Gender gender,
             BirthDate birthDate,
             String password,
             String phone,
             Set<Role> roles
            ) {
        return new UserBuilder(firstName, lastName, email, gender, birthDate, password, phone, roles);
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

    public UserBuilder withPhotoUrl(String url) {
        this.photoUrl = PhotoUrl.from(url);
        return this;
    }

    public User build() {
        return User.create(this);
    }
}
