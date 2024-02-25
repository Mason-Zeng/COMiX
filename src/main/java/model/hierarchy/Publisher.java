package model.hierarchy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Comic;

public class Publisher implements ComicHolder {
    private String name;
    private Map<String, Series> series;
    private Collection collection;

    public Publisher(String name) {
        this.name = name;
        series = new HashMap<>();
    }

    public Publisher(String name, Collection collection) {
        new Publisher(name);
        setCollection(collection);
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    } 

    public String getName() {
        return name;
    }
    
    public void addSeries(Series series) {
        this.series.put(series.getSeriesTitle(), series);
        series.setPublisher(this);
    }

    public void delSeries(Series series) {
        this.series.remove(series.getSeriesTitle());
    }

    public Series getSeries(String name) {
        return series.get(name);
    }

    public boolean seriesExists(String name) {
        return series.containsKey(name);
    }

    @Override
    public BigDecimal getValue() {
        return series.values().stream()
            .map(series -> series.getValue())
            .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public int getIssueCount() {
        return series.values().stream()
            .mapToInt(Series::getIssueCount)
            .sum();
    }

    @Override
    public List<Comic> getIssues() {
        return series.values().stream()
            .map(Series::getIssues)
            .collect(ArrayList::new, List::addAll, List::addAll);
    }

    @Override
    public void delSelf() {
        collection.delPublisher(this);
        collection = null;
    }

    @Override
    public List<ComicHolder> getChildren() {
        return new ArrayList<>(series.values());
    }
}
