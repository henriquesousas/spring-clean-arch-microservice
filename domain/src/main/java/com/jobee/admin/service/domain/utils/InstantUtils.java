package com.jobee.admin.service.domain.commons.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantUtils {
    private InstantUtils() {
    }

    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
