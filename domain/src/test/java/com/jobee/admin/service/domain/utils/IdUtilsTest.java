package com.jobee.admin.service.domain.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdUtilsTest {


    @Test
    public void giveAValidUUid_whenCallValidate_shouldReturnTrue() {
        Assertions.assertTrue(IdUtils.isValid("2f9d7582d2164f43b9ce14ed2b4903b1"));
    }

    @Test
    public void giveAnInValidUUid_whenCallValidate_shouldReturnFalse() {
        Assertions.assertFalse(IdUtils.isValid("2f9d7582d2164f43b9ce14ed2b "));
    }
}
