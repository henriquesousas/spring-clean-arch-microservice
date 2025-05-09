package com.jobee.admin.service.domain.genre;

import com.jobee.admin.service.domain.shared.AggregateRoot;
import com.jobee.admin.service.domain.shared.validation.ValidationHandler;

import java.time.Instant;

public class Genre extends AggregateRoot<GenreId> {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Genre(GenreId genreId) {
        super(genreId);
    }


    @Override
    public void validate(ValidationHandler handler) {

    }
}
