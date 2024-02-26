package controller.hierarchy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import controller.Comic;

public class Publisher implements ComicHolder {
    private String name;
    private List<Series> series;

    public Publisher(String name) {
        series = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void addSeries(Series series) {
        this.series.add(series);
    }

    @Override
    public BigDecimal getValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }

    @Override
    public int getIssueCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIssueCount'");
    }

    @Override
    public List<Comic> getIssues() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIssues'");
    }
}
