package com.jobee.admin.service.domain.shared;

public abstract class AggregateRoot<ID extends  Identifier> extends Entity<ID>{

    protected AggregateRoot(ID id) {
        super(id);
    }
}
