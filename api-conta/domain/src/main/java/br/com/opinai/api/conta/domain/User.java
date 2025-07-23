package br.com.opinai.api.conta.domain;

import br.com.opinai.api.conta.domain.valueobjects.*;
import com.opinai.shared.domain.AggregateRoot;
import com.opinai.shared.domain.utils.EnumUtils;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.validation.Error;
import com.opinai.shared.domain.validation.ValidationHandler;
import lombok.Getter;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Getter
public class User extends AggregateRoot<UserId> {
    private String firstName;
    private String lastName;
    private Phone phone;
    private Email email;
    private Gender gender;
    private BirthDate birthdate;
    private Password password;
    private PhotoUrl photoUrl;
    private boolean isActive;
    private Set<Role> roles;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private User(
            final UserId userId,
            final String firstName,
            final String lastName,
            final Email email,
            final Gender gender,
            final BirthDate birthdate,
            final Phone phone,
            final Password password,
            final boolean isActive,
            final PhotoUrl photoUrl,
            final Set<Role> roles,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(userId);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = email;
        this.gender = gender;
        this.birthdate = birthdate;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
        this.isActive = isActive;
        this.photoUrl = photoUrl;
        this.createdAt = Objects.requireNonNullElse(createdAt, InstantUtils.now());
        this.updatedAt = Objects.requireNonNullElse(updatedAt, InstantUtils.now());
        this.deletedAt = deletedAt;
        validate(notification);
    }

    public static User create(UserBuilder builder) {
        return new User(
                builder.getUserId(),
                builder.getFirstName(),
                builder.getLastName(),
                Email.from(builder.getEmail()),
                builder.getGender(),
                builder.getBirthDate(),
                Phone.from(builder.getPhone()),
                Password.from(builder.getPassword()),
                builder.isActive(),
                builder.getPhotoUrl(),
                builder.getRoles(),
                builder.getCreatedAt(),
                builder.getUpdatedAt(),
                builder.getDeletedAt());
    }

    public void changeFirstName(String firstName) {
        if (failIfInactive())  return;

        this.updatedAt = InstantUtils.now();
        this.firstName = firstName;
        validate(notification);

    }

    public void changeLastName(String firstName) {
        if (failIfInactive())  return;

        this.updatedAt = InstantUtils.now();
        this.lastName = firstName;
        validate(notification);
    }

    public void changeEmail(String email) {
        if (failIfInactive())  return;
        this.updatedAt = InstantUtils.now();
        this.email = Email.from(email);
        validate(notification);
    }

    public void changeBirthdate(String date) {
        if (failIfInactive())  return;

        this.updatedAt = InstantUtils.now();
        this.birthdate = BirthDate.from(date);
        validate(notification);
    }

    public void changeGender(String gender) {

        if (failIfInactive())  return;

        this.updatedAt = InstantUtils.now();
        this.gender = EnumUtils.of(Gender.values(), gender);
        validate(notification);
    }

    public void changePassword(String password) {
        this.password = Password.from(password);
        validate(notification);
    }

    public void addPhoto(String url) {
        if (failIfInactive())  return;
        this.photoUrl = PhotoUrl.from(url);
        this.updatedAt = InstantUtils.now();
    }

    private boolean failIfInactive() {
        if (!isActive()) {
            this.notification.append(new Error("Usuário inativo"));
            return true;
        }
        return false;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }

}
