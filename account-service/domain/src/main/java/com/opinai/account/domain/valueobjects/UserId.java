package com.opinai.account.domain.valueobjects;

import com.opinai.shared.domain.Identifier;
import com.opinai.shared.domain.utils.IdUtils;

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
