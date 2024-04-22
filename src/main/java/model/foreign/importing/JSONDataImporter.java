package model.foreign.importing;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import model.Character;
import model.Comic;
import model.Creator;
import model.foreign.DataImporter;
import model.hierarchy.*;
import model.marking.*;

public class JSONDataImporter implements DataImporter {

    @Override
    public List<Marking> importFile(FileReader file) {
        JSONTokener reader = new JSONTokener(file);
        List<Marking> result = new ArrayList<>();
        JSONArray jArr = (JSONArray)reader.nextValue();
        for (Object obj : jArr) {

            JSONObject jObj = (JSONObject)obj;
            Marking comic = new Comic(jObj.getString("title"),
                                jObj.getString("issue"),
                                jObj.getString("description"),
                                jObj.getBigDecimal("value"),
                                LocalDate.parse(jObj.getString("date")));

            Publisher pub = new Publisher(jObj.getString("publisher"));
            Series series = new Series(jObj.getString("series"), pub);
            Volume vol = new Volume(jObj.getString("volume"), series);
            comic.setVolume(vol);

            JSONArray creators = jObj.getJSONArray("creators");
            for (Object creator : creators) {
                comic.addCreator(new Creator((String)creator));
            }
            JSONArray characters = jObj.getJSONArray("characters");
            for (Object character : characters) {
                comic.addCharacter(new Character((String)character));
            }

            String format = jObj.getString("format");
            try {
                comic = MarkingFactory.formatComic(comic, format);
            }
            catch (StringIndexOutOfBoundsException e){

            }
            result.add(comic);
        }

        return result;
    }

}
