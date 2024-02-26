package model.hierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import model.marking.Marking;

public class Series implements ComicHolder{
    private String name;
    private Publisher publisher;
    private Map<Integer, Volume> volumes;

    public Series(String name) {
        this.name = name;
        this.volumes = new HashMap<>();
    }

    public Series(String name, Publisher publisher) {
        this(name);
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
        vol.setSeries(this);
    }
  
    public String getSeriesTitle() {
        return name;
    }

    public void delVolume(Volume vol) {
        volumes.remove(vol.getVolumeNumber());
    }
    
    public Volume getVolume(Integer vol_num) {
        return volumes.get(vol_num);
    }

    public boolean volumeExists(Integer vol_num) {
        return volumes.containsKey(vol_num);
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

    public List<Marking> getIssues() {
        return volumes.values().stream()
            .map(Volume::getIssues)
            .collect(ArrayList::new, List::addAll, List::addAll);
    }

    @Override
    public void delSelf() {
        publisher.delSeries(this);
        publisher = null;
    }

    @Override
    public List<ComicHolder> getChildren() {
        return new ArrayList<>(volumes.values());
    }
}
