package model.foreign.importing;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.*;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import model.Comic;
import model.Creator;
import model.foreign.DataImporter;
import model.hierarchy.*;
import model.marking.Marking;
import model.marking.MarkingFactory;

public class CSVDataImporter implements DataImporter {

    private final CellProcessor[] processors = {
        new NotNull(), // title
        new Optional(), // description
        new NotNull(), // issue
        new NotNull(), // volume
        new NotNull(), // series
        new NotNull(), // publisher
        new ParseDate("MMM dd, yyyy", true),
        new Optional(), // creators
        new Optional(), // format
        new ParseBigDecimal() // value
    };

    @Override
    public List<Marking> importFile(FileReader file) {
        List<Marking> result = new ArrayList<>();

        CsvListReader listReader = null;
        try {
            listReader = new CsvListReader(file, CsvPreference.STANDARD_PREFERENCE);

            listReader.getHeader(true);
            
            List<Object> comicList;

            while ((comicList = listReader.read(processors)) != null) {
                Marking comic = new Comic((String)comicList.get(0), 
                                        (String)comicList.get(2), 
                                        (String)comicList.get(1), 
                                        (BigDecimal)comicList.get(9), 
                                        ((Date)comicList.get(6)).toLocalDate());

                Publisher pub = new Publisher((String)comicList.get(5));
                Series series = new Series((String)comicList.get(4), pub);
                Volume vol = new Volume((String)comicList.get(3), series);
                comic.setVolume(vol);

                for (String creator : ((String)comicList.get(7)).split(" | ")) {
                    comic.addCreator(new Creator(creator));
                }
                
                String format = ((String)comicList.get(8));
                comic = MarkingFactory.formatComic(comic, format);
                result.add(comic);
            }
            listReader.close();
        }
        catch (IOException e) {}
        return result;
    }

}
