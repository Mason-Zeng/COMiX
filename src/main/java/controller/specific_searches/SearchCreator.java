package controller.specific_searches;

import java.util.ArrayList;
import java.util.List;

import controller.search.SpecificSearch;
import model.Creator;
import model.marking.Marking;

public class SearchCreator implements SpecificSearch {

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
            List<Creator> creators = comic.getCreators();
            for (Creator creator: creators){
                if (query.equals(creator.getName().toLowerCase())){
                    comics.add(comic);
                    break;
                }
            }
        }
        return comics;
    }

    @Override
    public List<Marking> partialSearch(List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        for (Marking comic : data){
            List<Creator> creators = comic.getCreators();
            for (Creator creator : creators){
                String name = creator.getName();
                name = name.toLowerCase();
                if (name.contains(query)){
                    comics.add(comic);
                    break;
                }
            }
        }
        return comics;
    }
}
