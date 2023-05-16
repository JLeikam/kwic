package org.kwic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indexer {
    private EventManager eventManager;
    private static final Logger logger = LogManager.getLogger(Indexer.class);

    public Indexer(EventManager eventManager) {
        this.eventManager = eventManager;
        eventManager.subscribe(EventType.PREPROCESSING_COMPLETE, this::handlePreprocessingCompleteEvent);
        logger.info("Subscribing: PREPROCESSING_COMPLETE event");
    }

    private void handlePreprocessingCompleteEvent(Event event) {
        logger.info("Handling: PREPROCESSING_COMPLETE event");
        List<String> processedLines = (List<String>) event.getData();
        Map<String, List<String>>  indexData = buildIndex(processedLines);
        Event indexingEvent = new Event(EventType.INDEXING_COMPLETE, indexData);
        logger.info("Publishing: INDEXING_COMPLETE event");
        eventManager.publish(indexingEvent);
    }

    private Map<String, List<String>> buildIndex(List<String> processedLines) {
        Map<String, List<String>> index = new HashMap<>();
        for (int i = 0; i < processedLines.size(); i++) {
            String line = processedLines.get(i);
            String[] keywords = line.split("\\s+");
            for (String keyword : keywords) {
                List<String> linesWithKeyword = index.getOrDefault(keyword, new ArrayList<>());
                if (!linesWithKeyword.contains(line)) {
                    linesWithKeyword.add(line);
                }
                index.put(keyword, linesWithKeyword);
            }
        }
        return index;
    }
}
