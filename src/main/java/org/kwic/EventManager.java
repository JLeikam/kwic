package org.kwic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<EventType, List<EventHandler>> eventHandlers;

    public EventManager() {
        eventHandlers = new HashMap<>();
    }

    public void subscribe(EventType eventType, EventHandler eventHandler) {
        List<EventHandler> handlers = eventHandlers.getOrDefault(eventType, new ArrayList<>());
        handlers.add(eventHandler);
        eventHandlers.put(eventType, handlers);
    }

    public void publish(Event event) {
        List<EventHandler> handlers = eventHandlers.getOrDefault(event.getEventType(), new ArrayList<>());
        for (EventHandler handler : handlers) {
            handler.handleEvent(event);
        }
    }
}
