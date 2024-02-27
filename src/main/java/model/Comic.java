package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.hierarchy.*;
import model.marking.Marking;

public class Comic implements Marking{
    private String title;
    private Volume volume;
    private int issueNum;
    private LocalDate pub_date;
    private List<Creator> creators;
    private List<Character> characters;
    private String description;
    private BigDecimal value;

    public Comic(String title, int issueNum, String description, BigDecimal value, LocalDate pubDate, Volume volume) {
        this(title, issueNum, description, value, pubDate);
        this.volume = volume;
    }
    public Comic(String title, int issueNum, String description, BigDecimal value, LocalDate pubDate) {
        this.title = title;
        this.issueNum = issueNum;
        this.description = description;
        this.value = value;
        this.pub_date = pubDate;
        creators = new ArrayList<>();
        characters = new ArrayList<>();
    }

    /**
     * Copy constructor
     * @param other The comic to copy
     */
    public Comic(Comic other) {
        this( other.getTitle(), 
            other.getIssueNumber(), 
            other.getDescription(), 
            other.getValue(), 
            other.getDate());
    }

    public String getTitle() {
        return title;
    }

    public Volume getVolume() {
        return volume;
    }

    public Series getSeries() {
        return volume.getSeries();
    }

    public Publisher getPublisher() {
        return volume.getPublisher();
    }

    public String getPublisherName() {
        return getPublisher().getName();
    }

    public String getSeriesTitle() {
        return getSeries().getSeriesTitle();
    }

    public int getVolumeNumber() {
        return volume.getVolumeNumber();
    }

    public int getIssueNumber() {
        return issueNum;
    }

    public LocalDate getDate() {
        return pub_date;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void addCreator(Creator creator){
        creators.add(creator);
    }

    public void setVolume(Volume vol) {
        this.volume = vol;
    }

    public Comic getComic() {
        return this;
    }
}
