package com.jobee.admin.service.domain.user.valueobjects;

import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.validation.Error;
import com.jobee.admin.service.domain.user.RoleType;

public class Role extends ValueObject<String> {

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public static Role from(String value) {
        return new Role(value);
    }


    protected void selfValidate() {
        final var role = RoleType.of(value);
        if (role.isEmpty()) {
            notification.append(new Error(" %s não é um acesso válido".formatted(value)));
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
