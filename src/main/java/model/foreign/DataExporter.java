package model.foreign;

import java.io.FileWriter;
import java.util.List;

import model.marking.Marking;

public interface DataExporter {
    public void exportFile(List<Marking> data, FileWriter file);
} 
