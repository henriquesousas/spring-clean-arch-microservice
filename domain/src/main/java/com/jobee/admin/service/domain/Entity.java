package com.jobee.admin.service.domain;

import com.jobee.admin.service.domain.validation.ValidationHandler;
import com.jobee.admin.service.domain.validation.handler.Notification;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class Entity<ID extends Identifier> {
    protected final ID id;
    protected Notification notification = Notification.create();

    protected Entity(final ID id) {
        Objects.requireNonNull(id, "id should not be null");
        this.id = id;
    }

    public abstract void validate(ValidationHandler handler);

    public ID getAggregateId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity<?> entity)) return false;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //    @Override
//    public boolean equals(final Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        final Entity<?> entity = (Entity<?>) o;
//        return Objects.equals(id, entity.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
}
