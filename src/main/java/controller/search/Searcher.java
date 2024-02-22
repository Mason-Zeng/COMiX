package controller.search;

import java.util.ArrayList;
import java.util.List;

import controller.Comic;

public interface Searcher {
    public List<Comic> searchData(String query, ArrayList<Comic> data, String input);
}
