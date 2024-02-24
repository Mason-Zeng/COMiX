package controller.hierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import controller.Comic;

public class Series implements ComicHolder{
    private String name;
    private Publisher publisher;
    private Map<Integer, Volume> volumes;

    public Series(String name) {
        this.name = name;
        this.volumes = new HashMap<>();
    }

    public Series(String name, Publisher publisher) {
        new Series(name);
        setPublisher(publisher);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void addVolume(Volume vol) {
        volumes.put(vol.getVolumeNumber(), vol);
    }

    public void delVolume(Volume vol) {
        volumes.remove(vol.getVolumeNumber());
    }

    public String getSeriesTitle() {
        return name;
    }

    public BigDecimal getValue() {
        return volumes.values().stream()
            .map(volume -> volume.getValue())
            .reduce(new BigDecimal(0), BigDecimal::add);
    }

    public int getIssueCount() {
        return volumes.values().stream()
            .mapToInt(Volume::getIssueCount)
            .sum();
    }

    public List<Comic> getIssues() {
        return volumes.values().stream()
            .map(Volume::getIssues)
            .collect(ArrayList::new, List::addAll, List::addAll);
    }

    @Override
    public void delSelf() {
        publisher.delSeries(this);
    }
}
