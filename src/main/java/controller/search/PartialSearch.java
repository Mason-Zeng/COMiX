package controller.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Marking> oldMarks = new ArrayList<>();
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
                int issueNumber = comic.getIssueNumber();
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
                if (query.contains(series)){
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
                if (query.contains(series)){
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
                if (query.contains(series)){
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
                if (query.contains(series)){
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
        while (!(marking instanceof Comic)) {
            oldMarks.add(point);
            point = point.getMarking();
        }
        return oldMarks;
    }

}
