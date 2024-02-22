package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import controller.search.PartialSearch;
import controller.search.Searcher;
import controller.sort.DefaultSorter;

public class ComicSearcher {

    private ArrayList<Comic> data;
    private Comparator<Comic> sorter;
    private Searcher searcher;

    public ComicSearcher(){
        this.sorter = new DefaultSorter();
        this.searcher = new PartialSearch();
    }

    public List<Comic> search(String query){
        List<Comic> results = searcher.searchData(query, data);
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
