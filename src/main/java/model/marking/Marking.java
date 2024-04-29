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
    public BigDecimal getTrueValue();
    public String getTitle();
    public String getPublisherName();
    public String getSeriesTitle();
    public String getVolumeNumber();
    public String getIssueNumber();
    public List<Creator> getCreators();
    public List<Character> getCharacters();
    public LocalDate getDate();
    public void setVolume(Volume vol);
    public Volume getVolume();
    public Series getSeries();
    public Publisher getPublisher();
    public String getDescription();
    public Marking getMarking();
    public void addCreator(Creator creator);
    public void addCharacter(Character character);
    public void setValue(BigDecimal value);
    public void setTitle(String title);
    public void setIssueNumber(String number);
    public void removeAllCreators();
    public void setDate(LocalDate date);
    public double extractIssueValue();
    public void setSeries(Series series);
    public void setPublisher(String name);
    public void setDescription(String desc);
}
