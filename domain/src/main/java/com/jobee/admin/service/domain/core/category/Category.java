package com.jobee.admin.service.domain.core.category;

import com.jobee.admin.service.domain.AggregateRoot;
import com.jobee.admin.service.domain.validation.ValidationHandler;
import lombok.Getter;


import java.time.Instant;
import java.util.Objects;

@Getter
public class Category extends AggregateRoot<CategoryId> implements Cloneable {

    private String name;
    private String description;
    private boolean isActive;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public Category(
            CategoryId id,
            String name,
            String description,
            boolean isActive,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.deletedAt = deletedAt;
    }

    public Category activate() {
        if (this.isActive) return this;

        this.isActive = true;
        this.updatedAt = Instant.now();
        this.deletedAt = null;
        return this;
    }

    public Category deactivate() {
        if (!this.isActive) return this;

        final var now = Instant.now();
        this.isActive = false;
        this.updatedAt = now;
        this.deletedAt = now;
        return this;
    }

    public Category update(String name, String description) {
        if (this.isActive) {
            this.name = name;
            this.description = description;
            this.updatedAt = Instant.now();
        }
        return this;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    @Override
    public Category clone() {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public CategoryId getId() {
        return this.id;
    }

}