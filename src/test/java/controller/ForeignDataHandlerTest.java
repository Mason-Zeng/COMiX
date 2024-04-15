package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import model.marking.Marking;

public class ForeignDataHandlerTest {
    @TempDir
    File tempDir = new File("data/test");
    
    @Test
    /**
     * Test both import and
     * export for json handling.
     * Uses original data file
     * and compares old importer
     * to new one.
     * @throws IOException
     */
    void testDataParityJSON() throws IOException {
        File tmpJson = new File(tempDir, "test.json");
        ForeignDataHandler handler = ForeignDataHandler.getHandler();

        // Load from initital provided CSV
        List<Marking> expected = handler.importData(new File("data/comics.json"));

        handler.exportData(tmpJson, expected);

        List<Marking> actual = handler.importData(tmpJson);

        // Easier to handle than by raw comparison between lists
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    void testDataParityXML() throws IOException {
        File tmpXml = new File(tempDir, "test.xml");
        ForeignDataHandler handler = ForeignDataHandler.getHandler();

        // Load from initital provided CSV
        List<Marking> expected = handler.importData(new File("data/comics.xml"));

        handler.exportData(tmpXml, expected);

        List<Marking> actual = handler.importData(tmpXml);

        // Easier to handle than by raw comparison between lists
        assertEquals(expected.toString(), actual.toString());

    }
    
    @Test
    void testDataParityCSV() throws IOException {
        File tmpXml = new File(tempDir, "test.csv");
        ForeignDataHandler handler = ForeignDataHandler.getHandler();

        // Load from initital provided CSV
        List<Marking> expected = handler.importData(new File("data/comics.csv"));


        handler.exportData(tmpXml, expected);

        List<Marking> actual = handler.importData(tmpXml);

        // Easier to handle than by raw comparison between lists
        assertEquals(expected.toString(), actual.toString());
    }
}
