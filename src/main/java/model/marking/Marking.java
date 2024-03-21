package model.marking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import model.Creator;
import model.hierarchy.Publisher;
import model.hierarchy.Series;
import model.hierarchy.Volume;
import model.Character;
/*
 * The component within the decorator pattern.
 * Defines the behavior for all types of marking.
 */
public interface Marking{
    public BigDecimal getValue();
    public String getTitle();
    public String getPublisherName();
    public String getSeriesTitle();
    public int getVolumeNumber();
    public int getIssueNumber();
    public List<Creator> getCreators();
    public List<Character> getCharacters();
    public LocalDate getDate();
    public void setVolume(Volume vol);
    public Volume getVolume();
    public Series getSeries();
    public Publisher getPublisher();
    public Marking getMarking();
}
