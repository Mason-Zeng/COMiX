package controller.specific_searches;

import java.util.List;

import controller.search.SpecificSearch;
import model.marking.Marking;

public class SearchIssueNumber implements SpecificSearch {

    @Override
    public List<Marking> searchData(String type, List<Marking> data, String query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchData'");
    }

    @Override
    public List<Marking> exactSearch(List<Marking> data, String query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exactSearch'");
    }

    @Override
    public List<Marking> partialSearch(List<Marking> data, String query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialSearch'");
    }

}
