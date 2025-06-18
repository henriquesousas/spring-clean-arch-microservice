package com.jobee.admin.service.infrastructure.reviewanalysis.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class ReviewCreatedMessageResult {
    private Payload payload;

    @JsonProperty("occurred_on")
    private Instant occurredOn;

    @JsonProperty("event_version")
    private int eventVersion;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
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
}
