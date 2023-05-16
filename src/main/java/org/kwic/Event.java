package org.kwic;

public class Event {
    private EventType eventType;
    private Object data;

    public Event(EventType eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Object getData() {
        return data;
    }
}