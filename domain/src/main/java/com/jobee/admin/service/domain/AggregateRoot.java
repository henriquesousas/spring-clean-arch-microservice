package com.jobee.admin.service.domain;


import com.jobee.admin.service.domain.events.DomainEvent;

import java.util.*;
import java.util.function.Consumer;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    private final Set<DomainEvent> events = new HashSet<>();
    private final Map<String, List<Consumer<DomainEvent>>> localHandlers = new HashMap<>();
    //private final Set<DomainEvent> dispatchedEvents = new HashSet<>();

    protected AggregateRoot(ID id) {
        super(id);
    }

    protected void applyEvent(DomainEvent event) {
        events.add(event);
        emitLocal(event);
    }

    protected void registerEventHandler(String eventName, Consumer<DomainEvent> handler) {
        localHandlers.computeIfAbsent(eventName, k -> new ArrayList<>()).add(handler);
    }

    public List<DomainEvent> getEvents() {
        return new ArrayList<>(events);
    }

    public void clearEvents() {
        events.clear();
    }

    private void emitLocal(DomainEvent event) {
        String eventName = event.getClass().getSimpleName();
        List<Consumer<DomainEvent>> handlers = localHandlers.getOrDefault(eventName, Collections.emptyList());
        for (Consumer<DomainEvent> handler : handlers) {
            handler.accept(event);
        }
    }


//    public void applyEvent(DomainEvent event) {
//        events.add(event);
//        emitLocal(event);
//    }
//
//    public void registerHandler(String eventName, Consumer<DomainEvent> handler) {
//        localHandlers.computeIfAbsent(eventName, k -> new ArrayList<>()).add(handler);
//    }
//
//    public List<DomainEvent> getEvents() {
//        return new ArrayList<>(events);
//    }
//
//    public void clearEvents() {
//        events.clear();
//        dispatchedEvents.clear();
//    }
//
//    private void emitLocal(DomainEvent event) {
//        String eventName = event.getClass().getSimpleName();
//        List<Consumer<DomainEvent>> handlers = localHandlers.getOrDefault(eventName, Collections.emptyList());
//        for (Consumer<DomainEvent> handler : handlers) {
//            handler.accept(event);
//        }
//    }
//
//    public void markEventAsDispatched(DomainEvent event) {
//        dispatchedEvents.add(event);
//    }
//
//    public List<DomainEvent> getUnDispatchedEvents() {
//        List<DomainEvent> result = new ArrayList<>();
//        for (DomainEvent event : events) {
//            if (!dispatchedEvents.contains(event)) {
//                result.add(event);
//            }
//        }
//        return result;
//    }
}
