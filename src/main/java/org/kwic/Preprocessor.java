package org.kwic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Preprocessor {
    private EventManager eventManager;
    private Set<String> stopwords;
    private static final Logger logger = LogManager.getLogger(Preprocessor.class);


    public Preprocessor(EventManager eventManager) {
        this.eventManager = eventManager;
        logger.info("Subscribing: TEXT_RECEIVED event");
        eventManager.subscribe(EventType.TEXT_RECEIVED, this::handleTextReceivedEvent);
        stopwords = new HashSet<>(); // Not adding stopwords for now
    }

    private void handleTextReceivedEvent(Event event) {
        logger.info("Handling: TEXT_RECEIVED event");
        String text = (String) event.getData();
        String[] lines = text.split("\\r?\\n");
        List<String> processedLines = new ArrayList<>();
        for (String line : lines) {
            String processedLine = preprocess(line);
            processedLines.add(processedLine);
        }
        Event preprocessingEvent = new Event(EventType.PREPROCESSING_COMPLETE, processedLines);
        logger.info("Publishing: PREPROCESSING_COMPLETE event");
        eventManager.publish(preprocessingEvent);
    }

    private String preprocess(String text) {
        String normalizedText = text.toLowerCase();
        String textWithoutPunctuation = normalizedText.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] tokens = textWithoutPunctuation.split("\\s+");
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            if (!stopwords.contains(token)) {
                filteredTokens.add(token);
            }
        }
        String processedText = String.join(" ", filteredTokens);
        return processedText;
    }
}
