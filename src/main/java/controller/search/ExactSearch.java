package controller.search;

import java.util.ArrayList;
import java.util.List;

import controller.SetSpecificSearch;
import controller.specific_searches.SearchAuthenticates;
import controller.specific_searches.SearchCreator;
import controller.specific_searches.SearchGaps;
import controller.specific_searches.SearchGrades;
import controller.specific_searches.SearchIssueNumber;
import controller.specific_searches.SearchPublicationDate;
import controller.specific_searches.SearchPublisher;
import controller.specific_searches.SearchRuns;
import controller.specific_searches.SearchSeriesTitle;
import controller.specific_searches.SearchSigns;
import controller.specific_searches.SearchSlabs;
import controller.specific_searches.SearchStoryTitle;
import model.marking.Marking;


/**
 * ExactSearch implements the Searcher class.
 * ExactSearch in the Strategy pattern is a Concrete Strategy.
 * The ExactSearch implements an algorithm that searches for oldMarkss that exactly equal the query.
 * 
 * @author Ayden Dazo
 */
public class ExactSearch implements Searcher{
    String type = "exact";
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
        SpecificSearch specificSearcher = null;
        SetSpecificSearch setter = new SetSpecificSearch(specificSearcher);
        if(input.equals("series_title")){
            setter.setSpecificSearch(new SearchSeriesTitle());
        }
        else if(input.equals("issue_number")){
            setter.setSpecificSearch(new SearchIssueNumber());
        }
        else if(input.equals("story_title")){
            setter.setSpecificSearch(new SearchStoryTitle());
        }
        else if(input.equals("publisher")){
            setter.setSpecificSearch(new SearchPublisher());
        }
        else if(input.equals("date")){
            setter.setSpecificSearch(new SearchPublicationDate());
        }
        else if(input.equals("creator")){
            setter.setSpecificSearch(new SearchCreator());
        }
        else if(input.equals("sign")){
            setter.setSpecificSearch(new SearchSigns());
        }
        else if(input.equals("authenticate")){
            setter.setSpecificSearch(new SearchAuthenticates());
        }
        else if(input.equals("grade")){
            setter.setSpecificSearch(new SearchGrades());
        }
        else if(input.equals("slab")){
            setter.setSpecificSearch(new SearchSlabs());
        }
        else if(input.equals("runs")){
            setter.setSpecificSearch(new SearchRuns());
        }
        else if(input.equals("gaps")){
            setter.setSpecificSearch(new SearchGaps());
        }
        comics = setter.executeSearch(type, data, query);
        return comics;
    }  
        
    
    // Any 2 instances of ExactSearch are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ExactSearch) ? true : false;
    }
}
