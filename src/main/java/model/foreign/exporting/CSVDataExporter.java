package model.foreign.exporting;

import java.io.FileWriter;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;

import model.foreign.DataExporter;
import model.marking.Marking;

public class CSVDataExporter implements DataExporter {

    private static String[] HEADERS;
    private CsvBeanWriter writer;

    @Override
    public void exportFile(List<Marking> data, FileWriter file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportFile'");
    }
    
}
