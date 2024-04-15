package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.hierarchy.*;
import model.marking.Marking;

public class Comic implements Marking{
    private String title;
    private Volume volume;
    private String issueNum;
    private LocalDate pub_date;
    private List<Creator> creators;
    private List<Character> characters;
    private String description;
    private BigDecimal value;

    public Comic(String title, String issueNum, String description, BigDecimal value, LocalDate pubDate, Volume volume) {
        this(title, issueNum, description, value, pubDate);
        this.volume = volume;
    }
    public Comic(String title, String issueNum, String description, BigDecimal value, LocalDate pubDate) {
        this.title = title;
        this.issueNum = issueNum;
        this.description = description;
        this.value = value;
        this.pub_date = pubDate;
        creators = new ArrayList<>();
        characters = new ArrayList<>();
    }

    public Comic(ComicOutput output) {
        title = output.getFullTitle();
        String seriesString = output.getSeries();
        int index = seriesString.indexOf(", Vol. ");
        Series series;
        if (index != -1) {
            volume = new Volume(seriesString.substring(index+7));
            series = new Series(seriesString.substring(0, index));
        } else {
            volume = new Volume("1");
            series = new Series(output.getSeries());
        }
        Publisher publisher = new Publisher(output.getPublisher());
        
        
        publisher.addSeries(series);
        series.addVolume(volume);
        try {
            issueNum = output.getIssue();
        } catch (NumberFormatException e) {
            issueNum = "1";
        }
        description = output.getVariantDescription();
        value = new BigDecimal(0);
        pub_date = LocalDate.parse(output.getAddedDate(), 
            DateTimeFormatter.ofPattern("LLL dd, uuuu"));
        creators = Arrays.stream(output.getCreators().split("\\s\\|\\s")).map(Creator::new).toList();
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

    public String getVolumeNumber() {
        return volume.getVolumeNumber();
    }

    public String getIssueNumber() {
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

    public BigDecimal getTrueValue() {
        return value;
    }

    public void addCreator(Creator creator){
        creators.add(creator);
    }

    public void setVolume(Volume vol) {
        this.volume = vol;
    }

    @Override
    public String toString() {
        return "\rSeries Title: " + getSeriesTitle() +
                "\n\tVolume Number: " + getVolumeNumber() + 
                "\n\tIssue Number: " + getIssueNumber() +
                "\n\tStory Title: " + getTitle() +
                "\n\tPublication Date: " + getDate().toString() +
                // gets creators and remaps to its name, then concatenates all of them
                "\n\tCreators: " + getCreators().stream().map(Creator::getName).reduce("", (String total, String s) -> s + ", " + total) +
                "\n"
        ;
    }
    @Override
    public Marking getMarking() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Marking) {
            Marking other = (Marking)obj;
            return getSeriesTitle().equals(other.getSeriesTitle()) 
            && getVolumeNumber() == other.getVolumeNumber() 
            && getTitle().equals(other.getTitle())
            && getDate().equals(other.getDate())
            && getCreators().equals(other.getCreators());
        }
        return false;
    }
}
