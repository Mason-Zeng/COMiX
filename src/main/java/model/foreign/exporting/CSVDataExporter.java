package model.foreign.exporting;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

import model.Creator;
import model.foreign.DataExporter;
import model.marking.Marking;
import model.marking.MarkingHandler;

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
    @SuppressWarnings("resource")
    public void exportFile(List<Marking> data, FileWriter file) {
        CSVWriter writer = new CSVWriter(file);
        writer.writeNext(HEADERS, false);
        for (Marking comic : data) {
            String[] comicArr = {
                comic.getTitle(),
                comic.getDescription(),
                comic.getIssueNumber(),
                comic.getVolumeNumber(),
                comic.getSeriesTitle(),
                comic.getPublisherName(),
                comic.getDate().toString(),
                comic.getCreators()
                    .stream()
                    .map(Creator::getName)
                    .collect(Collectors.joining(" | ")),
                MarkingHandler.getFormat(comic),
                comic.getTrueValue().toString()
            };
            writer.writeNext(comicArr, false);
        }
    }
}
