package controller.specific_searches;

import java.util.ArrayList;
import java.util.List;

import controller.search.SpecificSearch;
import model.marking.Authenticate;
import model.marking.Grade;
import model.marking.Marking;
import model.marking.MarkingHandler;
import model.marking.Slab;

public class SearchSlabs implements SpecificSearch {

    @Override
    public List<Marking> searchData(String type, List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        if(type.equals("exact")){
            comics = exactSearch(data, query);
            return comics;
        }
        return comics = partialSearch(data, query);
    }

    @Override
    public List<Marking> exactSearch(List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        List<Marking> sameSeriesTittles = new ArrayList<>();
        List<Marking> oldMarks = new ArrayList<>();
        for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (query.equals(series)){
                    sameSeriesTittles.add(comic);
                }
            }
        for(int i=0; i<sameSeriesTittles.size();i++){
            oldMarks = MarkingHandler.getMarkingOrder(sameSeriesTittles.get(i));
            for(int j=0; j<oldMarks.size(); j++){
                if(oldMarks.get(j) instanceof Slab){
                    comics.add(sameSeriesTittles.get(i));
                }
            }
        }
        return comics;
    }

    @Override
    public List<Marking> partialSearch(List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        List<Marking> sameSeriesTittles = new ArrayList<>();
        List<Marking> oldMarks = new ArrayList<>();
        for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    sameSeriesTittles.add(comic);
                }
            }
        for(int i=0; i<sameSeriesTittles.size();i++){
            oldMarks = MarkingHandler.getMarkingOrder(sameSeriesTittles.get(i));
            for(int j=0; j<oldMarks.size(); j++){
                if(oldMarks.get(j) instanceof Slab){
                    comics.add(sameSeriesTittles.get(i));
                }
            }
        }
        return comics;
    }

}
