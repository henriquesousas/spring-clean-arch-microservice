package br.com.opinai.api.conta.domain.valueobjects;

import com.opinai.shared.domain.ValueObject;
import com.opinai.shared.domain.utils.InstantUtils;
import com.opinai.shared.domain.validation.Error;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.Objects;

public class BirthDate extends ValueObject<String> {

    private final String value;

    private BirthDate(final String value) {
        this.value = Objects.requireNonNull(value);
        selfValidate();

    }

    private void selfValidate() {

        try {
            final var currentDate = InstantUtils.now();
            final var birthDate = InstantUtils.toInstant(this.value);
            final var birthLocalDate = birthDate.atZone(ZoneId.systemDefault()).toLocalDate();
            final var currentLocalDate = currentDate.atZone(ZoneId.systemDefault()).toLocalDate();

            int age = Period.between(birthLocalDate, currentLocalDate).getYears();
            if (age < 16) {
                this.getNotification().append(new Error("Só é possível se cadastrar a partir dos 16 anos"));
            }

        } catch (Exception e) {
            this.getNotification().append(new Error("Data de nascimento inválida"));
        }
    }

    public static BirthDate from(String date) {
        return new BirthDate(date);
    }

    public static BirthDate from(Instant date) {
        return new BirthDate( InstantUtils.toDateFormat(date) );
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
