package controller;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;

public class Series implements ComicHolder{
    private String name;
    private Publisher publisher;
    private List<Volume> volumes;

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void addVolume(Volume vol) {
        volumes.add(vol);
    }

    public String getSeriesTitle() {
        return name;
    }

    public BigDecimal getValue() {
        return volumes.stream()
            .map(volume -> volume.getValue())
            .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public int getIssueCount() {
        return volumes.stream()
            .mapToInt(Volume::getIssueCount)
            .sum();
    }

    public List<Comic> getIssues() {
        return volumes.stream()
            .map(Volume::getIssues)
            .collect(ArrayList::new, List::addAll, List::addAll);
    }
}
