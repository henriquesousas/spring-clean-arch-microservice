package com.jobee.admin.service.application;

public abstract class UnitUseCase<IN> {
    public abstract void execute(IN in);
}
