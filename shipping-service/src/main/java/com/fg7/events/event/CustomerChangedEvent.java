package com.fg7.events.event;

public class CustomerChangedEvent {

    private String typeName;
    private String eventType;
    private Long customerId;
    private String tokenId;

    public CustomerChangedEvent() {
    }

    public CustomerChangedEvent(String typeName, String eventType, Long customerId, String tokenID) {
        this.typeName = typeName;
        this.eventType = eventType;
        this.customerId = customerId;
        this.tokenId = tokenID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public String toString() {
        return "CustomerChangedEvent{" +
                "typeName='" + typeName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", customerId=" + customerId +
                ", tokenId='" + tokenId + '\'' +
                '}';
    }
}
