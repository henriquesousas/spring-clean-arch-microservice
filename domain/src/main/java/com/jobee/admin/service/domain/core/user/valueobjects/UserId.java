package com.jobee.admin.service.domain.core.user.valueobjects;

import com.jobee.admin.service.domain.Identifier;
import com.jobee.admin.service.domain.utils.IdUtils;

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
