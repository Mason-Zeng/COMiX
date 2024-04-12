package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import model.Comic;
import model.ComicOutput;
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

        // Load from initital provided CSV
        List<Marking> expected = ComicOutput.loadFromCSV("data/comics.csv")
        .stream()
        .map(Comic::new)
        .map(Marking.class::cast)
        .toList();

        ForeignDataHandler handler = ForeignDataHandler.getHandler();

        handler.exportData(tmpJson, expected);

        List<Marking> actual = handler.importData(tmpJson);

        // Easier to handle than by raw comparison between lists
        assertEquals(expected.toString(), actual.toString());

    }
    
}
