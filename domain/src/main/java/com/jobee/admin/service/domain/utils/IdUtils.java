package com.jobee.admin.service.domain.commons.utils;

import java.util.UUID;

public final  class IdUtils {

    private IdUtils() {}

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
