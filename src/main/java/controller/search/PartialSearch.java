package controller.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Creator;
import model.marking.Marking;

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
        }
        return comics;
    }
    
    // Any 2 instances of Partial Search are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PartialSearch) ? true : false;
    }

}
