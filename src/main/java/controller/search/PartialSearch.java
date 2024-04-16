package controller.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.sort.IssueNumberSorter;
import model.Comic;
import model.Creator;
import model.marking.Authenticate;
import model.marking.Grade;
import model.marking.Marking;
import model.marking.Sign;
import model.marking.Slab;

/**
 * PartialSearch implements the Searcher class.
 * PartialSearch in the Strategy pattern is a Concrete Strategy.
 * The PartialSearch implements an algorithm that searches for results that contain or match the query.
 * 
 * @author Ayden Dazo
 */
public class PartialSearch implements Searcher{


    /**
     * The algorithm that will search through the data and return comics that contain or match the query.
     * The algorithm returns different comics depending on the method of searching.
     * 
     * @param query What the user searches for
     * @param data List of comics, either from database or from personal collection
     * @param input The method of searching
     * @return A list of comics that match exactly to the query
     */
    @Override
    public List<Marking> searchData(String query, List<Marking> data, String input) {
        query = query.toLowerCase();
        List<Marking> comics = new ArrayList<>();
        List<Marking> tempList = new ArrayList<>();
        ArrayList<Marking> partialList = new ArrayList<>();
        ArrayList<Marking> oldMarks = new ArrayList<>();
        List<Marking> additions = new ArrayList<>(); 
        ArrayList<String> titles = new ArrayList<>();
        switch(input){

            case "series_title":
            for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "issue_number":
            for (Marking comic: data){
                String issueNumber = comic.getIssueNumber();
                if (String.valueOf(issueNumber).contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "story_title":
            for (Marking comic: data){
                String title = comic.getTitle();
                title = title.toLowerCase();
                if (title.contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "publisher":
            for (Marking comic : data){
                String name = comic.getPublisherName();
                name = name.toLowerCase();
                if (name.contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "date":
            for (Marking comic : data){
                LocalDate date = comic.getDate();
                if (date.toString().contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "creator":
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
            break;

            case "sign":
            for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    tempList.add(comic);
                }
            }
            for(int i=0; i<tempList.size();i++){
                oldMarks = getMarkings(tempList.get(i));
                for(int j=0; j<oldMarks.size(); j++){
                    if(oldMarks.get(j) instanceof Sign){
                        comics.add(tempList.get(i));
                    }
                }
            }
            break;

            case "authenticate":
            for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    tempList.add(comic);
                }
            }
            for(int i=0; i<tempList.size();i++){
                oldMarks = getMarkings(tempList.get(i));
                for(int j=0; j<oldMarks.size(); j++){
                    if(oldMarks.get(j) instanceof Authenticate){
                        comics.add(tempList.get(i));
                    }
                }
            }
            break;

            case "grade":
            for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    tempList.add(comic);
                }
            }
            for(int i=0; i<tempList.size();i++){
                oldMarks = getMarkings(tempList.get(i));
                for(int j=0; j<oldMarks.size(); j++){
                    if(oldMarks.get(j) instanceof Grade){
                        comics.add(tempList.get(i));
                    }
                }
            }
            break;

            case "slab":
            for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    tempList.add(comic);
                }
            }
            for(int i=0; i<tempList.size();i++){
                oldMarks = getMarkings(tempList.get(i));
                for(int j=0; j<oldMarks.size(); j++){
                    if(oldMarks.get(j) instanceof Slab){
                        comics.add(tempList.get(i));
                    }
                }
            }
            break;

            case "runs":
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
            break;

            case "gaps":
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
        }
        return comics;
    }
    
    // Any 2 instances of Partial Search are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PartialSearch) ? true : false;
    }

    //Gets all the markings applied to a comic
    public ArrayList<Marking> getMarkings(Marking marking){
        ArrayList<Marking> oldMarks = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            oldMarks.add(point);
            point = point.getMarking();
        }
        return oldMarks;
    }

    //Gets all the different series titles in a list.
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

    //Gets all comics with specific series title
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
