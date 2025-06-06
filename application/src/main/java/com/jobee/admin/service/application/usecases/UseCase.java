package com.jobee.admin.service.application.usecases;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN in);
}