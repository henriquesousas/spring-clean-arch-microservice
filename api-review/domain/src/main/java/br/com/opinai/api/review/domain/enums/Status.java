package br.com.opinai.api.review.domain.enums;

import com.opinai.shared.domain.Valuable;
import lombok.Getter;

@Getter
public enum Status implements Valuable<String> {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IN_ANALYSIS("IN_ANALYSIS");

    private final String value;

    Status(final String value) {
        this.value = value;
    }
}
