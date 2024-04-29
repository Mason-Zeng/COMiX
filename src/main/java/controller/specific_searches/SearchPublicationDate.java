package controller.specific_searches;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.search.SpecificSearch;
import model.marking.Marking;

public class SearchPublicationDate implements SpecificSearch {

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
        for (Marking comic : data){
            LocalDate date = comic.getDate();
            if (query.equals(date.toString())){
                comics.add(comic);
            }
        }
        return comics;
    }

    @Override
    public List<Marking> partialSearch(List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        for (Marking comic : data){
            LocalDate date = comic.getDate();
            if (date.toString().contains(query)){
                comics.add(comic);
            }
        }
        return comics;
    }

}
