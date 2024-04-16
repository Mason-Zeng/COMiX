package controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
        DataExporter exporter = DataExporterEnum.getExporter(fileType);
        FileWriter writer = new FileWriter(file);
        exporter.exportFile(comics, writer);
        writer.flush();
        writer.close();
    }

    public List<Marking> importData(File file) throws IOException {
        String fileType = getFileType(file);
        DataImporter importer = DataImporterEnum.getImporter(fileType);
        FileReader reader = new FileReader(file);
        List<Marking> result = importer.importFile(reader);
        reader.close();
        return result;
    }
}
