package controller.search;

import java.util.List;

import model.Comic;

/**
 * Searcher is an interface that allows for polymorphism.
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
     * @return List of Comics
     */
    public List<Comic> searchData(String query, List<Comic> data, String input);
}
