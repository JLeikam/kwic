package org.kwic;

/**
 * EventType is an enum that represents the type of event.
 * For the KWIC implementation, we have three types of events:
 * 1. TEXT_RECEIVED: This event is fired when the text has been received from the text file.
 * 2. PREPROCESSING_COMPLETE: This event is fired when the preprocessing component has finished preprocessing the text.
 * 3. INDEXING_COMPLETE: This event is fired when the indexing component has finished indexing the text.
 * If we wanted to go beyond just the indexing implementation, we'd add more events here for sorting etc.
 */
public enum EventType {
    TEXT_RECEIVED,
    PREPROCESSING_COMPLETE,
    INDEXING_COMPLETE
}
