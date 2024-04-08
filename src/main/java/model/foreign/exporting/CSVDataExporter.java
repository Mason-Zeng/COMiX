package model.foreign.exporting;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import model.Creator;
import model.foreign.DataExporter;
import model.marking.Grade;
import model.marking.Marking;
import model.marking.MarkingFactory;

public class CSVDataExporter implements DataExporter {

    private final CellProcessor[] processors = {
        new NotNull(), // title
        new Optional(), // description
        new ParseInt(), // issue
        new ParseInt(), // volume
        new NotNull(), // series
        new NotNull(), // publisher
        new ParseDate("MMM dd, yyyy", true),
        new Optional(), // creators
        new Optional(), // format
        new ParseBigDecimal() // value
    };

    private String[] HEADERS = {
        "title",
        "description",
        "issue",
        "volume",
        "series",
        "publisher",
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
                
                List<String> format = new ArrayList<>();
                List<Marking> marks = new MarkingFactory().getMarkingOrder(comic);
                for (Marking mark : marks) {
                    switch (mark.getClass().getName()) {
                        case "Grade":
                            format.add("G(" + ((Grade)mark).getGrade() + ")");
                            break;
                        case "Slab":
                            format.add("S");
                            break;
                        case "Auth": // TODO: Change on integration
                            // TODO: Add Auth Comics
                            format.add("A");
                            break;
                        case "Signed": // TODO: Change on Intergration
                            // TODO: Add Signed comics
                            break;
                        default:
                            break;
                    }
                }
    
                comicArr[8] = String.join(" ", format);
    
                writer.write(comicArr, processors);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }
    
}
