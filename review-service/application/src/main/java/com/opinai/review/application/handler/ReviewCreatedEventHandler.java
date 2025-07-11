package com.opinai.review.application.handler;


import com.opinai.shared.domain.events.DomainEvent;
import com.opinai.shared.domain.events.handler.DomainEventHandler;

public class ReviewCreatedEventHandler implements DomainEventHandler {

    @Override
    public void handler(DomainEvent event) {
        System.out.println("Execute some bussines logic when review has been created...");
    }
}
