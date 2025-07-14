package com.opinai.reviewanalyze.infrastructure.amqp;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;


public class EventMessage<T> {

    private T payload;

    @JsonProperty("occurred_on")
    private Instant occurredOn;

    @JsonProperty("event_version")
    private int eventVersion;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("service_origin")
    private String serviceOrigin;

    public EventMessage() {}

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(Instant occurredOn) {
        this.occurredOn = occurredOn;
    }

    public int getEventVersion() {
        return eventVersion;
    }

    public void setEventVersion(int eventVersion) {
        this.eventVersion = eventVersion;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getServiceOrigin() {
        return serviceOrigin;
    }

    public void setServiceOrigin(String serviceOrigin) {
        this.serviceOrigin = serviceOrigin;
    }
}