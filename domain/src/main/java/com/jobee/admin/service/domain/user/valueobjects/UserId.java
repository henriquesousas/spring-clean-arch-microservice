package com.jobee.admin.service.domain.user.valueobjects;

import com.jobee.admin.service.domain.commons.Identifier;
import com.jobee.admin.service.domain.commons.utils.IdUtils;

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
