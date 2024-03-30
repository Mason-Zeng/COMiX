package model.foreign.exporting;

import java.io.FileWriter;
import java.util.List;

import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;

import model.foreign.DataExporter;
import model.marking.Marking;

public class XMLDataExporter implements DataExporter {

    private static WebRowSet WRS;
    private XmlWriter writer;

    @Override
    public void exportFile(List<Marking> data, FileWriter file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportFile'");
    }

}
