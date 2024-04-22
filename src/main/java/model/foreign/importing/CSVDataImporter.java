package model.foreign.importing;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import model.Character;
import model.Comic;
import model.Creator;
import model.foreign.DataImporter;
import model.hierarchy.*;
import model.marking.Marking;
import model.marking.MarkingFactory;

public class CSVDataImporter implements DataImporter {

    private Marking arrToComic(String[] array) {
        Marking comic = new Comic(  array[0],
                                    array[2],
                                    array[1],
                                    new BigDecimal(array[9]),
                                    LocalDate.parse(array[6]));   
        Publisher pub = new Publisher(array[5]);
        Series series = new Series(array[4], pub);
        Volume vol = new Volume(array[3], series);
        comic.setVolume(vol);
       
        for (String creator : array[7].split(" \\| ")) {
            comic.addCreator(new Creator(creator));
        }

        if (array[10].length() != 0){
            for (String character : array[10].split(" \\| ")) {
                comic.addCharacter(new Character(character));
            }
        }

        comic = MarkingFactory.formatComic(comic, array[8]);
        return comic;
    }


    @Override
    public List<Marking> importFile(FileReader file) {
        CSVReader reader = new CSVReader(file);
        List<Marking> result = new ArrayList<>();
        try {
            reader.skip(1); //read headers
            List<String[]> comicArrs = reader.readAll();
            result = comicArrs
                .stream()
                .map(this::arrToComic)
                .toList();

            reader.close();
        } catch (CsvValidationException e) {} 
        catch (IOException e) {} 
        catch (CsvException e) {}
        
        return result;
    }
}
