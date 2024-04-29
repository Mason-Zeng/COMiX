package model.foreign.exporting;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

import model.Character;
import model.Creator;
import model.foreign.DataExporter;
import model.marking.Marking;
import model.marking.MarkingHandler;

public class JSONDataExporter implements DataExporter {

    @Override
    public void exportFile(List<Marking> data, FileWriter file) {
        List<JSONObject> jArr = new ArrayList<>();
        for (Marking comic : data) {
            JSONObject obj = new JSONObject();
            obj.put("title", comic.getTitle());
            obj.put("description", comic.getDescription());
            obj.put("issue", comic.getIssueNumber());
            obj.put("volume", comic.getVolumeNumber());
            obj.put("series", comic.getSeriesTitle());
            obj.put("publisher", comic.getPublisherName()); 
            obj.put("value", comic.getTrueValue());
            obj.put("date", comic.getDate().toString());

            JSONArray creators = new JSONArray();
            for (Creator creator : comic.getCreators()) {
                creators.put(creator.getName());
            }

            obj.put("creators", creators);

            JSONArray characters = new JSONArray();
            for (Character character : comic.getCharacters()) {
                creators.put(character.getName());
            }

            obj.put("characters", characters);
            
            String format = MarkingHandler.getFormat(comic);
            obj.put("format", format);
            jArr.add(obj);
        }
        JSONWriter writer = new JSONWriter(file);
        writer.array();
            for (JSONObject obj : jArr) {
                writer.value(obj);
            }
        writer.endArray();
    }

}
