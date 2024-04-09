package controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Comic;
import model.ComicOutput;
import model.foreign.*;
import model.marking.Marking;

public class ForeignDataHandler {

    private static ForeignDataHandler handler;
    
    public static ForeignDataHandler getHandler() {
        if (handler == null) {
            handler = new ForeignDataHandler();
        }
        return handler;
    }

    private ForeignDataHandler() {};

    private String getFileType(File file) {
        String filePath = file.getAbsolutePath();
        int i = filePath.lastIndexOf(".");
        return filePath.substring(i+1).toUpperCase();
    }

    public void exportData(File file, List<Marking> comics) throws IOException {
        String fileType = getFileType(file);
        DataExporter exporter = Enum.valueOf(DataExporterEnum.class, fileType).getExporter();
        FileWriter writer = new FileWriter(file);
        exporter.exportFile(comics, writer);
        writer.flush();
        writer.close();
    }

    public List<Marking> importData(File file) throws IOException {
        String fileType = getFileType(file);
        DataImporter importer = Enum.valueOf(DataImporterEnum.class, fileType).getImporter();
        FileReader reader = new FileReader(file);
        List<Marking> result = importer.importFile(reader);
        reader.close();
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<Marking> comics = ComicOutput.loadFromCSV("data/comics.csv")
        .stream()
        .map(Comic::new)
        .map(Marking.class::cast)
        .toList();
        ForeignDataHandler handler = getHandler();
        try {
            handler.exportData(new File("data/comics.json"), comics);
            
        } catch (Exception e) {
            System.err.println("huh");
        }

        List<Marking> newComics = handler.importData(new File("data/comics.json"));
        assert comics.equals(newComics);
    }
}
