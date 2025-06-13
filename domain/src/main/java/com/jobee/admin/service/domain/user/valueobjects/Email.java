package com.jobee.admin.service.domain.user.valueobjects;
;
import com.jobee.admin.service.domain.ValueObject;
import com.jobee.admin.service.domain.validation.Error;

import java.util.regex.Pattern;

public class Email extends ValueObject<String> {

    private final String value;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private Email(String value) {
        this.value = value;
        selfValidate();
    }

    public static Email from(String value) {
        return new Email(value);
    }


    protected void selfValidate() {
        final var isValid =  this.value != null && EMAIL_PATTERN.matcher(this.value).matches();
        if(!isValid){
            this.notification.append(new Error("Email inválido"));
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
