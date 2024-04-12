package model.foreign.importing;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
                                jObj.getInt("issue"),
                                jObj.getString("description"),
                                jObj.getBigDecimal("value"),
                                LocalDate.parse(jObj.getString("date")));

            Volume vol = new Volume(jObj.getInt("volume"));
            Series series = new Series(jObj.getString("series"));
            Publisher pub = new Publisher(jObj.getString("publisher"));
            comic.setVolume(vol);
            vol.setSeries(series);
            series.setPublisher(pub);

            JSONArray creators = jObj.getJSONArray("creators");
            for (Object creator : creators) {
                comic.addCreator(new Creator((String)creator));
            }

            String format = jObj.getString("format");
            // comic = MarkingFactory.formatComic(comic, format);
            result.add(comic);
        }

        return result;
    }

}
