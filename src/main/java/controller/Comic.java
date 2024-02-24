package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.hierarchy.Publisher;
import controller.hierarchy.Series;
import controller.hierarchy.Volume;
import controller.marking.MarkImplement;

public class Comic implements MarkImplement {
    private String title;
    private Volume volume;
    private int issueNum;
    private Date pub_date;
    private List<Creator> creators;
    private List<Character> characters;
    private String description;
    private BigDecimal value;

    public Comic() {
        creators = new ArrayList<>();
    } 
   public Comic(String title, int issueNum, String description, BigDecimal value, LocalDate pubDate) {
        this.title = title;
        this.issueNum = issueNum;
        this.description = description;
        this.value = value;
        creators = new ArrayList<>();
        characters = new ArrayList<>();
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

    public String getSeriesTitle() {
        return getSeries().getSeriesTitle();
    }

    public int getVolumeNumber() {
        return volume.getVolumeNumber();
    }

    public int getIssueNumber() {
        return issueNum;
    }

    public Date getDate() {
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
}
