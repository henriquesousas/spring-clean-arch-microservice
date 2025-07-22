package br.com.opinai.api.conta.domain;

import com.opinai.shared.domain.Valuable;
import lombok.Getter;

@Getter
public enum Gender implements Valuable<String> {
    MALE("MASCULINO"),
    FEMALE("FEMININO");

    private final String value;

    Gender(final String value) {
        this.value = value;
    }
}
