package org.kwic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class InputReader {
    private EventManager eventManager;
    private static final Logger logger = LogManager.getLogger(InputReader.class);

    public InputReader(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void readTextFromFile(String relativePath) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(relativePath);
            if (is == null) {
                throw new FileNotFoundException("File not found: " + relativePath);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            String text = sb.toString();
            Event event = new Event(EventType.TEXT_RECEIVED, text);
            logger.info("Publishing: TEXT_RECEIVED event");
            eventManager.publish(event);
        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("Error reading text from file: " + e.getMessage());
        }
    }
}
