package controller;

import java.util.Comparator;
import java.util.List;

import controller.search.PartialSearch;
import controller.search.Searcher;
import controller.sort.DefaultSorter;
import model.Comic;

public class ComicSearcher {

    private List<Comic> data;
    private Comparator<Comic> sorter;
    private Searcher searcher;

    public ComicSearcher(List<Comic> data){
        this.sorter = new DefaultSorter();
        this.searcher = new PartialSearch();
        this.data = data;
    }

    public List<Comic> search(String query, String input){
        List<Comic> results = searcher.searchData(query, data, input);
        results.sort(sorter);
        return results;
    }

    public void setSorter(Comparator<Comic> sorter){
        this.sorter = sorter;
    }

    public void setSearcher(Searcher searcher){
        this.searcher = searcher;
    }

    //These 2 methods are protected since used mainly for testing.
    protected Comparator<Comic> getSorter(){
        return sorter;
    }

    protected Searcher getSearcher(){
        return searcher;
    }
}
