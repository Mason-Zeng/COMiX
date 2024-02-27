package controller;

import java.util.Comparator;
import java.util.List;

import controller.search.PartialSearch;
import controller.search.Searcher;
import controller.sort.DefaultSorter;
import model.Comic;

/**
 * A class that searches and sorts through either a database or a personal collection.
 * Sorting is included within the searching.
 * In the strategy pattern, ComicSearcher is a Context.
 * @author Ayden Dazo
 */
public class ComicSearcher {

    /**
     * A list of comics from either a database or a personal collection.
     */
    private List<Comic> data;

    /**
     * A Comprator that acts as a sorter. 
     * Comparator is a Strategy in the Strategy pattern
     */
    private Comparator<Comic> sorter;

    /**
     * Searcher is a Strategy in the Strategy pattern.
     */
    private Searcher searcher;

    /**
     * A constructor for a ComicSearcher class.
     * Instantiates the sorter as the DefaultSorter class, a Concrete Strategy.
     * Instantiates the searcher as the PartialSearch class, a Concrete Strategy.
     * @param data a list of comics from somewhere.
     */
    public ComicSearcher(List<Comic> data){
        this.sorter = new DefaultSorter();
        this.searcher = new PartialSearch();
        this.data = data;
    }

    /**
     * Searches through the data, then sorts the results from the search.
     * @param query What the user wants to search for
     * @param input The method of searching
     * @return a list of sorted comics that contained/matched the query.
     */
    public List<Comic> search(String query, String input){
        List<Comic> results = searcher.searchData(query, data, input);
        results.sort(sorter);
        return results;
    }

    /**
     * Sets the sorter to a different Concrete Strategy.
     * @param sorter The new sorter that is being changed to
     */
    public void setSorter(Comparator<Comic> sorter){
        this.sorter = sorter;
    }

    /**
     * Sets the searcher to a different Concrete Strategy.
     * @param searcher The new searcher that is being changed to
     */
    public void setSearcher(Searcher searcher){
        this.searcher = searcher;
    }
    
    /**
     * A method that gets the sorter.
     * Is protected since it is mainly used for testing.
     * @return The current sorter
     */
    protected Comparator<Comic> getSorter(){
        return sorter;
    }

    /**
     * A method that gets the searcher. 
     * Is protected since it is mainly used for testing.
     * @return The current searcher
     */
    protected Searcher getSearcher(){
        return searcher;
    }
}
