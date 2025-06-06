package com.jobee.admin.service.application.usecases;

public abstract class UnitUseCase<IN> {
    public abstract void execute(IN in);
}
