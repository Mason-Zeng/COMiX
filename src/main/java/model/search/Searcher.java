package model.search;

import java.util.List;

import controller.Comic;

public interface Searcher {
    public List<Comic> searchData(String query, List<Comic> data, String input);
}
