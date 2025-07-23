package br.com.opinai.api.conta.domain.infrastructure.cryptography;

import br.com.opinai.api.conta.application.cryptography.Encrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BCryptEncoder implements Encrypt {


    private final PasswordEncoder encoder;

    public BCryptEncoder(final PasswordEncoder encoder) {
        this.encoder = Objects.requireNonNull(encoder);
    }

    @Override
    public String encrypt(String value) {
        return encoder.encode(value);
    }
}
