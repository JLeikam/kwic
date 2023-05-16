package org.kwic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class OutputWriter {
    private EventManager eventManager;
    private static final Logger logger = LogManager.getLogger(OutputWriter.class);

    public OutputWriter(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.subscribe(EventType.INDEXING_COMPLETE, this::handleIndexingCompleteEvent);
        logger.info("Subscribing: INDEXING_COMPLETE event");
    }

    private void handleIndexingCompleteEvent(Event event) {
        logger.info("Handling: INDEXING_COMPLETE event");
        Map<String, List<String>> indexData = (Map<String, List<String>>) event.getData();
        System.out.println("Index:");
        for (String keyword : indexData.keySet()) {
            System.out.println(keyword + ": " + indexData.get(keyword));
        }
    }
}
