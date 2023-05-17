package org.kwic;

/**
 * Event is a class that represents an event.
 * It is responsible for storing the event type and data.
 */
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