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
    protected int signCount;

    public ComicDecorator(Marking comic) {
        this.comic = comic;
    }

    public BigDecimal getValue(){
        return comic.getValue();
    }

    public Comic getComic() {
        return (Comic)comic;
    }

    @Override
    public String getTitle() {
        return getTitle();
    }

    @Override
    public String getPublisherName() {
        return getPublisherName();
    }

    @Override
    public String getSeriesTitle() {
        return getSeriesTitle();
    }

    @Override
    public int getVolumeNumber() {
        return comic.getVolumeNumber();
    }

    @Override
    public int getIssueNumber() {
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
    public Marking getMarking() {
        return comic;
    }

    public int getSignCount(){
        return signCount;
    }
}
