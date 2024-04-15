package model.foreign;

import java.io.FileReader;
import java.util.List;

import model.marking.Marking;

public interface DataImporter {
    public List<Marking> importFile(FileReader file);
}
