package model.foreign.exporting;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import model.Creator;
import model.foreign.DataExporter;
import model.marking.Marking;
import model.marking.MarkingFactory;

public class CSVDataExporter implements DataExporter {

    private String[] HEADERS = {
        "title",
        "description",
        "issue",
        "volume",
        "series",
        "publisher",
        "date",
        "creators",
        "format",
        "value"
    };

    @Override
    public void exportFile(List<Marking> data, FileWriter file) {
        CsvListWriter writer = new CsvListWriter(file, CsvPreference.STANDARD_PREFERENCE);
        try {
            writer.write(HEADERS);
            for (Marking comic : data) {
                Object[] comicArr = {
                    comic.getTitle(),
                    comic.getDescription(),
                    comic.getIssueNumber(),
                    comic.getVolumeNumber(),
                    comic.getSeriesTitle(),
                    comic.getPublisherName(),
                    comic.getDate(),
                    null,
                    null,
                    comic.getTrueValue()
                };
    
                comicArr[7] = comic.getCreators()
                                    .stream()
                                    .map(Creator::getName)
                                    .collect(Collectors.joining(" | "));
               
                comicArr[8] = MarkingFactory.getFormat(comic);
    
                writer.write(Arrays.asList(comicArr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }
    
}
