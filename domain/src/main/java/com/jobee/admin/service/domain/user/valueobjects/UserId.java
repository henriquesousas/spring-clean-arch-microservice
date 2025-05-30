package com.jobee.admin.service.domain.user.valueobjects;

import com.jobee.admin.service.domain.shared.Identifier;
import com.jobee.admin.service.domain.shared.exceptions.ValidationException;
import com.jobee.admin.service.domain.shared.utils.IdUtils;
import com.jobee.admin.service.domain.shared.validation.Error;

import static java.util.UUID.randomUUID;

public class UserId extends Identifier {

    private UserId(String value) {
        super(value);
    }

    public static UserId unique() {
        return new UserId(IdUtils.uuid());
    }

    public static UserId from(String value) {
        return new UserId(value);
    }
}
