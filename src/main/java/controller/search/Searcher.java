package controller.search;

import java.util.List;
 
import model.marking.Marking;

/**
 * Searcher is an interface that allows for polymorphism.
 * Searcher in the Strategy pattern is a Strategy.
 * Concrete implemenations of Searcher will contain algorithms for searching.
 * 
 * @author Ayden Dazo
 */
public interface Searcher {
    /**
     * A method that will contain algorithms for searching in a concrete implementation
     * 
     * @param query What should be searched for
     * @param data The List of Comics
     * @param input The method of Searching
     * @return List of Comics that were found in the search
     */
    public List<Marking> searchData(String query, List<Marking> data, String input);
}
