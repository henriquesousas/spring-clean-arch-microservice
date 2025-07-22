package br.com.opinai.api.conta.domain;

import com.opinai.shared.domain.Valuable;
import lombok.Getter;

@Getter
public enum Role implements Valuable<String> {
    USER("USER"),
    ADMIN("ADMIN"),
    MODERATOR("MODERATOR");

    private final String value;

    Role(final String value) {
        this.value = value;
    }

}
