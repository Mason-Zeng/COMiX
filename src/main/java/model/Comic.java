package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public Comic(ComicOutput output) {
        String full_title = output.getFullTitle();
        title = full_title.contains(", Vol. ") ? 
            full_title.substring(0, full_title.length() - 9) :
            full_title;
        Publisher publisher = new Publisher(output.getPublisher());
        Series series = new Series(output.getSeries());
        volume = full_title.contains(", Vol. ") ? 
            new Volume(Integer.parseInt(full_title.substring(full_title.length() - 1))) :
            new Volume(1);

        publisher.addSeries(series);
        series.addVolume(volume);
        try {
            issueNum = Integer.parseInt(output.getIssue());
        } catch (NumberFormatException e) {
            issueNum = 1;
        }
        description = output.getVariantDescription();
        value = new BigDecimal(0);
        pub_date = LocalDate.parse(output.getAddedDate(), 
            DateTimeFormatter.ofPattern("LLL dd, uuuu"));
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
