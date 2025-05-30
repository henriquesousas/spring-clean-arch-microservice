package com.jobee.admin.service.domain.review.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDING(0),
    APPROVED(1),
    REJECTED(2),
    IN_ANALYSIS(3);

    private final int value;

    Status(final int value) {
        this.value = value;
    }
}
