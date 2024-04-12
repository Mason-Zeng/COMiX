package controller.foreign;

import java.io.File;
import java.io.FileNotFoundException;
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

    public void exportData(File file, List<Marking> comics) throws IOException {
        String fileType = file.getAbsolutePath().split("\\.")[file.getAbsolutePath().split("\\.").length -1].toUpperCase();
        DataExporter exporter = Enum.valueOf(DataExporterEnum.class, fileType).getExporter();
        FileWriter writer = new FileWriter(file);
        exporter.exportFile(comics, writer);
    }

    public List<Marking> importData(File file) throws FileNotFoundException {
        String fileType = file.getAbsolutePath().split("\\.")[file.getAbsolutePath().split("\\.").length -1].toUpperCase();
        DataImporter importer = Enum.valueOf(DataImporterEnum.class, fileType).getImporter();
        FileReader reader = new FileReader(file);
        return importer.importFile(reader);
    }
}
