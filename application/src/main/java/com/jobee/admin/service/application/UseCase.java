package com.jobee.admin.service.application;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN in);
}