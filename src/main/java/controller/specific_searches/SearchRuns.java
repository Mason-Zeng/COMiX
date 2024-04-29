package controller.specific_searches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.search.SpecificSearch;
import controller.sort.IssueNumberSorter;
import model.marking.Marking;

public class SearchRuns implements SpecificSearch{
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
        List<Marking> additions = new ArrayList<>(); 
        for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (query.equals(series)){
                    sameSeriesTitles.add(comic);
                }
            }
            Collections.sort(sameSeriesTitles, new IssueNumberSorter());
            int runs = 1;
            if(sameSeriesTitles.size() < 12){
                return comics;
            }
            for(int i=0; i<sameSeriesTitles.size(); i++){
                if(i != sameSeriesTitles.size()-1){
                    if(sameSeriesTitles.get(i+1).extractIssueValue() - sameSeriesTitles.get(i).extractIssueValue() <= 1){
                        runs++;
                        additions.add(sameSeriesTitles.get(i));
                    }
                    else{
                        if(runs >= 12){
                            comics.addAll(additions);
                        }
                        additions.clear();
                        runs = 1;
                    }
                }
                if(i == sameSeriesTitles.size()-1){
                    additions.add(sameSeriesTitles.get(i));
                }
            }
            if(runs>= 12){
                comics.addAll(additions);
            }
            return comics;
    }

    @Override
    public List<Marking> partialSearch(List<Marking> data, String query) {
        List<Marking> comics = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        List<Marking> additions = new ArrayList<>(); 
        ArrayList<Marking> partialList = new ArrayList<>();
        int runs = 1;
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
                for(int j=0; j<CWST.size();j++){
                    if( j != CWST.size()-1){
                        if(CWST.get(j+1).extractIssueValue() - CWST.get(j).extractIssueValue() <= 1){
                            runs++;
                            additions.add(CWST.get(j));
                        }
                        else{
                            if(runs >= 12){
                                comics.addAll(additions);
                            }
                            additions.clear();
                            runs = 1;
                        }
                    }
                    if(j == CWST.size()-1){
                        additions.add(CWST.get(j));
                    }
                }
                if(runs>= 12){
                    comics.addAll(additions);
                }
                additions.clear();
                runs = 1;
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
