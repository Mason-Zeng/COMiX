package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.time.LocalDate;

import model.hierarchy.*;
import model.Comic;

public class CollectionManagerTest {
    private Collection collection;
    private CollectionManager collectionManager;
    private Publisher publisher;
    private Series series;
    private Volume volume;
    private Comic comic;

    @BeforeEach
    public void setup() {
        collection = new Collection("TestUser");
        collectionManager = new CollectionManager(collection);
        publisher = new Publisher("TestPublisher");
        series = new Series("TestSeries");
        volume = new Volume(1);
        comic = new Comic("TestComic", 1, "TestDescription", new BigDecimal(12), LocalDate.ofEpochDay(0));
        volume.addIssue(comic);
        series.addVolume(volume);
        publisher.addSeries(series);
    }
    
    @Test
    public void testAddIssue() {
        collectionManager.addIssue(comic);

        // Attempt grabbing all items in hierarchy
        Publisher publisher2 = assertDoesNotThrow(() -> collection.getPublisher("TestPublisher"));
        Series series2 = assertDoesNotThrow(() -> publisher2.getSeries("TestSeries"));
        Volume volume2 = assertDoesNotThrow(() -> series2.getVolume(1));
        assertDoesNotThrow(() -> volume2.getIssue("TestComic"));
    }

    @Test
    public void testDelIssue() {
        collectionManager.addIssue(comic);

        Publisher publisher2 = assertDoesNotThrow(() -> collection.getPublisher("TestPublisher"));
        Series series2 = assertDoesNotThrow(() -> publisher2.getSeries("TestSeries"));
        Volume volume2 = assertDoesNotThrow(() -> series2.getVolume(1));
        Comic comic2 = assertDoesNotThrow(() -> volume2.getIssue("TestComic"));
        
        collectionManager.delIssue(comic2);
        assertEquals(0, collection.getIssueCount());
    }


}
