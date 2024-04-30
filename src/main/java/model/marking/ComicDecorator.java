package model.marking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import model.Comic;
import model.Creator;
import model.Character;
import model.hierarchy.Publisher;
import model.hierarchy.Series;
import model.hierarchy.Volume;

public abstract class ComicDecorator implements Marking {
    protected Marking comic;

    public ComicDecorator(Marking comic) {
        this.comic = comic;
    }

    public BigDecimal getTrueValue() {
        return comic.getTrueValue();
    }

    public Comic getComic() {
        return (Comic)comic;
    }

    @Override
    public String getTitle() {
        return comic.getTitle(); //none
    }

    @Override
    public String getPublisherName() {
        return comic.getPublisherName(); //none
    }

    @Override
    public String getSeriesTitle() {
        return comic.getSeriesTitle();
    }

    @Override
    public String getVolumeNumber() {
        return comic.getVolumeNumber();
    }

    @Override
    public String getIssueNumber() {
        return comic.getIssueNumber();
    }

    @Override
    public List<Creator> getCreators() {
        return comic.getCreators();
    }

    @Override
    public List<Character> getCharacters() {
        return comic.getCharacters();
    }

    @Override
    public LocalDate getDate() {
        return comic.getDate();
    }

    @Override
    public void setVolume(Volume vol) {
        comic.setVolume(vol);
    }

    @Override
    public Volume getVolume() {
        return comic.getVolume();
    }

    @Override
    public Series getSeries() {
        return comic.getSeries();
    }

    @Override
    public Publisher getPublisher() {
        return comic.getPublisher();
    }

    @Override
    public String getDescription() {
        return comic.getDescription();
    }

    @Override
    public void addCreator(Creator creator) {
        comic.addCreator(creator);
    }

    @Override
    public double extractIssueValue(){
        return comic.extractIssueValue();
    }

    @Override
    public Marking getMarking() {
        return comic;
    }
    @Override
    public boolean equals(Object obj) {
        return comic.equals(obj);
    }

    @Override
    public void addCharacter(Character character) {
        comic.addCharacter(character);
    }
    
    public void setValue(BigDecimal value){
        comic.setValue(value);
    }

    @Override
    public void setTitle(String title) {
        comic.setTitle(title);
    }

    @Override
    public void setIssueNumber(String number) {
        comic.setIssueNumber(number);
    }

    @Override
    public void removeAllCreators() {
        comic.removeAllCreators();
    }

    @Override
    public void removeAllCharacters() {
        comic.removeAllCharacters();
    }

    @Override
    public void setDate(LocalDate date) {
        comic.setDate(date);
    }

    @Override
    public void setSeries(Series series) {
        comic.setSeries(series);
    }

    @Override
    public void setPublisher(String name) {
        comic.setPublisher(name);
    }

    @Override
    public void setDescription(String desc) {
        comic.setDescription(desc);
    }

    @Override
    public void setVolNum(String num) {
        comic.setVolNum(num);
    }
}
