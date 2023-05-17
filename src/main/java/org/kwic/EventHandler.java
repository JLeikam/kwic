package org.kwic;

/**
 * When a component wants to receive events, it must implement this interface.
 * For example, the Indexing component wants to receive events from the Preprocessing component.
 * So, it implements this interface and registers itself as a listener to the Preprocessing component.
 * E.g., eventManager.subscribe(EventType.PREPROCESSING_COMPLETE, this::handlePreprocessingCompleteEvent);
 */
public interface EventHandler {
    void handleEvent(Event event);
}
