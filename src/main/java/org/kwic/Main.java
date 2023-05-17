package org.kwic;

/**
 * Main is the entry point of the program.
 * It is responsible for creating the event manager and the other KWIC components.
 */
public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        InputReader inputReader = new InputReader(eventManager);
        Preprocessor preprocessor = new Preprocessor(eventManager);
        Indexer indexer = new Indexer(eventManager);
        OutputWriter outputWriter = new OutputWriter(eventManager);

        String filePath = "kwic-input.txt";
        inputReader.readTextFromFile(filePath);
    }
}
