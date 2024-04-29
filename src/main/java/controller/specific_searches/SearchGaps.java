package controller.specific_searches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.search.SpecificSearch;
import controller.sort.IssueNumberSorter;
import model.marking.Marking;

public class SearchGaps implements SpecificSearch {

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
        List<Marking> sameSeriesTitles = new ArrayList<>();
        for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (query.equals(series)){
                    sameSeriesTitles.add(comic);
                }
            }
            if(sameSeriesTitles.size() < 12){
                return comics;
            }
            Collections.sort(sameSeriesTitles, new IssueNumberSorter());
            comics.addAll(sameSeriesTitles);
            return comics;
    }

    @Override
    public List<Marking> partialSearch(List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        ArrayList<Marking> partialList = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (Marking comic : data){
            String series = comic.getSeriesTitle();
            series = series.toLowerCase();
            if (series.contains(query)){
                partialList.add(comic);
            }
        }
        titles = getSeriesTitles(partialList);
        for(int i=0; i<titles.size();i++){
            ArrayList<Marking> CWST = getComicsWithSeriesTitle(titles.get(i), partialList);
            Collections.sort(CWST, new IssueNumberSorter());
            if(CWST.size() <12){
                continue;
            }
            comics.addAll(CWST);
        }
        return comics;
    }

    /**
     * Gets all the different series titles in a list.
     * @param partialList List of all titles that contain the query including duplicaets
     * @return list of title that contain the query without duplicates
     */
    public ArrayList<String> getSeriesTitles(ArrayList<Marking> partialList){
        ArrayList<String> containedTitles = new ArrayList<>();
        for(int i=0; i<partialList.size(); i++){
            String title = partialList.get(i).getSeriesTitle();
            if(!(containedTitles.contains(title))){
                containedTitles.add(title);
            }
        }
        return containedTitles;
    }

    /**
     * Gets all comics with specific series title
     * @param title The title that we are trying to compare with
     * @param list list of comics that have the series titles from the query
     * @return list of comics with the specific series title
     */
    public ArrayList<Marking> getComicsWithSeriesTitle(String title, List<Marking> list ){
        ArrayList<Marking> CWST = new ArrayList<>();
        for (Marking comic : list){
            if (title.equals(comic.getSeriesTitle())){
                CWST.add(comic);
            }
        }
        return CWST;
    }
}
