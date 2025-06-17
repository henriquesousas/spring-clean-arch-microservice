package com.jobee.admin.service.domain.utils;

import java.util.UUID;

public final  class IdUtils {

    private IdUtils() {}

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }

    public static boolean isValid(final String value) {
        return value.trim().length() == 32;
    }
}
