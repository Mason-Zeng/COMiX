package controller.search;

import java.util.List;

import model.Comic;

public interface Searcher {
    //TODO need to add data for searchData as a parameter
    public List<Comic> searchData(String query);
}
