package br.com.opinai.api.conta.domain.infrastructure;

import br.com.opinai.api.conta.domain.Gender;
import br.com.opinai.api.conta.domain.Role;
import br.com.opinai.api.conta.domain.User;
import br.com.opinai.api.conta.domain.UserBuilder;
import br.com.opinai.api.conta.domain.valueobjects.BirthDate;
import com.opinai.shared.domain.utils.EnumUtils;
import com.opinai.shared.domain.utils.InstantUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "Users")
public class UserJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birthdate", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant birthDate;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;


    public static UserJpaEntity from(User user) {

        return new UserJpaEntity(
                user.getId().getValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone().getValue(),
                user.getEmail().getValue(),
                user.getGender().getValue(),
                InstantUtils.toInstant(user.getBirthdate().getValue()),
                user.getPassword().getValue(),
                user.getRoles()
                        .stream()
                        .map(value -> EnumUtils.of(Role.values(), value.getValue()).getValue())
                        .toList()
                        .get(0),
                user.getPhotoUrl().getValue(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    public User toAggregate() {
        return UserBuilder.create(
                getFirstName(),
                getLastName(),
                getEmail(),
                EnumUtils.of(Gender.values(), getGender()),
                BirthDate.from(getBirthDate()),
                getPassword(),
                getPhone(),
                Set.of(EnumUtils.of(Role.values(),getRole()))
        ).build();
    }
}
