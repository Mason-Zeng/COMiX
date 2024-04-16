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
 * ExactSearch implements the Searcher class.
 * ExactSearch in the Strategy pattern is a Concrete Strategy.
 * The ExactSearch implements an algorithm that searches for oldMarkss that exactly equal the query.
 * 
 * @author Ayden Dazo
 */
public class ExactSearch implements Searcher{


    /**
     * The algorithm that will search through the data and return comics that match exactly to the query.
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
        List<Marking> sameSeriesTitles = new ArrayList<>(); 
        List<Marking> additions = new ArrayList<>(); 
        ArrayList<Marking> oldMarks = new ArrayList<>();

        switch (input){
            
            case "series_title":
            for (Marking comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (query.equals(series)){
                    comics.add(comic);
                }
            }
            break;

            case "issue_number":
            for (Marking comic: data){
                String issue_number = comic.getIssueNumber();
                if (query.equals(String.valueOf(issue_number))){
                    comics.add(comic);
                }
            }
            break;

            case "story_title":
            for (Marking comic: data){
                String title = comic.getTitle();
                title = title.toLowerCase();
                if (query.equals(title)){
                    comics.add(comic);
                }
            }
            break;

            case "publisher":
            for (Marking comic : data){
                String name = comic.getPublisherName();
                name = name.toLowerCase();
                if (query.equals(name)){
                    comics.add(comic);
                }
            }
            break;

            case "date":
            for (Marking comic : data){
                LocalDate date = comic.getDate();
                if (query.equals(date.toString())){
                    comics.add(comic);
                }
            }
            break;

            case "creator":
            for (Marking comic : data){
                List<Creator> creators = comic.getCreators();
                for (Creator creator: creators){
                    if (query.equals(creator.getName().toLowerCase())){
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
                if (query.equals(series)){
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
                if (query.equals(series)){
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
                if (query.equals(series)){
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
                if (query.equals(series)){
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
            break;

            case "gaps":
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
        }
        return comics;
    }
    
    // Any 2 instances of ExactSearch are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ExactSearch) ? true : false;
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

}
