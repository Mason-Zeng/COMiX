package controller;

import java.util.List;

import controller.search.SpecificSearch;
import model.marking.Marking;

/**
 * A class that is able to switch to the different types of searches.
 * In the strategy pattern, SetSpecificSearch is a Context.
 * @author Mason Zeng
 */
public class SetSpecificSearch {
    private SpecificSearch specificSearch; /*The Specific type of search */

    public SetSpecificSearch(SpecificSearch search){
        this.specificSearch = search;
    }

    /**
     * Method in order to switch between different specifc searches
     * @param search the search object that will be changing forms
     */
    public void setSpecificSearch(SpecificSearch search){
        this.specificSearch = search;
    }

    /**
     * Method in charge of calling the search function in the concrete specific searchers
     * @param type Either partial or exact search
     * @param data The List of Comics
     * @param query What should be searched for
     * @return List of Comics that were found in the search
     */
    public List<Marking> executeSearch(String type, List<Marking> data, String query){
        return specificSearch.searchData(type, data, query);
    }
}
