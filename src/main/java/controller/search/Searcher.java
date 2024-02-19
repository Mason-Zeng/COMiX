package controller;

import java.util.List;

public interface Searcher {
    //TODO need to add data for searchData as a parameter
    public List<Comic> searchData(String query);
}
