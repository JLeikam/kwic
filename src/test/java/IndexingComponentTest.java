import org.kwic.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class IndexingComponentTest {
    @Test
    void handlePreprocessingCompleteEvent_SingleLineText() {
        EventManager eventManager = new EventManager();
        Indexer indexer = new Indexer(eventManager);
        Preprocessor preprocessor = new Preprocessor(eventManager);

        eventManager.subscribe(EventType.INDEXING_COMPLETE, event -> {
            Map<String, List<String>> index = (Map<String, List<String>>) event.getData();

            Assertions.assertTrue(index.containsKey("single"));
            Assertions.assertEquals(1, index.get("single").size());
            Assertions.assertEquals("this is a single line", index.get("single").get(0));
        });

        List<String> mockLines = List.of("This is a single line.");
        String mockText = String.join("\n", mockLines);
        Event textReceivedEvent = new Event(EventType.TEXT_RECEIVED, mockText);
        eventManager.publish(textReceivedEvent);
    }

    @Test
    void handlePreprocessingCompleteEvent_MultiLineText() {
        EventManager eventManager = new EventManager();
        Indexer indexer = new Indexer(eventManager);
        Preprocessor preprocessor = new Preprocessor(eventManager);

        eventManager.subscribe(EventType.INDEXING_COMPLETE, event -> {
            Map<String, List<String>> index = (Map<String, List<String>>) event.getData();

            Assertions.assertTrue(index.containsKey("example"));
            Assertions.assertEquals(2, index.get("example").size());
            Assertions.assertTrue(index.containsKey("line"));
            Assertions.assertEquals(2, index.get("line").size());
            Assertions.assertEquals("this is an example sentence", index.get("example").get(0));
            Assertions.assertEquals("another example sentence", index.get("example").get(1));
            Assertions.assertEquals("this is a single line", index.get("line").get(0));
            Assertions.assertEquals("yet another line of text", index.get("line").get(1));
        });


        List<String> mockLines = List.of(
                "This is an example sentence.",
                "Another example sentence.",
                "This is a single line.",
                "Yet another line of text."
        );
        String mockText = String.join("\n", mockLines);
        Event textReceivedEvent = new Event(EventType.TEXT_RECEIVED, mockText);
        eventManager.publish(textReceivedEvent);
    }


}
