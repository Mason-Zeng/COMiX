package model.marking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
    public Marking getMarking() {
        return comic;
    }
    @Override
    public boolean equals(Object obj) {
        return comic.equals(obj);
    }

    @Override
    public void setValue(BigDecimal value){
        comic.setValue(value);
    }

    /*
     * Returns the number of times the object
     * was wrapped in Sign
     */
    public int signCount(Marking marking){
        int count = 0;
        ArrayList<Marking> result = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            result.add(point);
            point = point.getMarking();
        }
        result.add(point);
        Collections.reverse(result);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Sign){
                count++;
            }
        }
        return count;
    }

    /*
     * Checks the marking to see if the object has 
     * been marked with Grade
     */
    public boolean isGrade(Marking marking) {
        ArrayList<Marking> result = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            result.add(point);
            point = point.getMarking();
        }
        result.add(point);
        Collections.reverse(result);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Grade){
                return true;
            }
        }
        return false;
    }

    /*
     * Checks the marking to see if the object has 
     * been marked with Authenticate
     */
    public boolean isAuthenticated(Marking marking) {
        ArrayList<Marking> result = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            result.add(point);
            point = point.getMarking();
        }
        result.add(point);
        Collections.reverse(result);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Authenticate){
                return true;
            }
        }
        return false;
    }
}
