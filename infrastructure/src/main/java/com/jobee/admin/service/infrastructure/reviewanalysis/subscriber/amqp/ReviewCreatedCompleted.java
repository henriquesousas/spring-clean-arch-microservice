package com.jobee.admin.service.infrastructure.reviewanalysis.subscriber.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewCreatedCompleted {

    @JsonProperty("aggregate_id")
    private String aggregateId;

    @JsonProperty("user_id")
    private String userId;

    private String type;

    public ReviewCreatedCompleted() {}

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
