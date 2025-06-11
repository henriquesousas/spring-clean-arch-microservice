package com.jobee.admin.service.application.handles.review;

import com.jobee.admin.service.application.events.DomainEventHandler;
import com.jobee.admin.service.domain.events.DomainEvent;

public class ReviewCreatedEventHandler implements DomainEventHandler {

    @Override
    public void handler(DomainEvent event) {
        System.out.println("Execute some bussines logic when review has been created...");
    }
}
