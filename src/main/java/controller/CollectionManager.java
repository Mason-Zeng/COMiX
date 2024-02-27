package controller;

import model.hierarchy.*;
import model.marking.Marking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.Comic;
import model.ComicOutput;

public class CollectionManager {
    private Collection collection;
    
    public CollectionManager(Collection collection) {
        this.collection = collection;
    }

    public void addIssue(ComicOutput output) {
        String full_title = output.getFullTitle();
        String title = full_title.contains(", Vol. ") ? 
            full_title.substring(0, full_title.length() - 9) :
            full_title;
        Publisher publisher = new Publisher(output.getPublisher());
        Series series = new Series(output.getSeries());
        Volume volume = full_title.contains(", Vol. ") ? 
            new Volume(Integer.parseInt(full_title.substring(full_title.length() - 1))) :
            new Volume(1);

        publisher.addSeries(series);
        series.addVolume(volume);
        int issueNum = Integer.parseInt(output.getIssue());
        String description = output.getVariantDescription();
        BigDecimal value = new BigDecimal(0);
        LocalDate pub_date = LocalDate.parse(output.getAddedDate(), 
            DateTimeFormatter.ofPattern("LLL dd, uuuu"));

        addIssue(new Comic(title, issueNum, description, value, pub_date, volume));
    }

    /**
     * Adds a comic to the personal 
     * collection of a user. 
     * 
     * The comic added is being assumed that
     * it doesnt exist within the collection.
     * 
     * @param comic
     * @return void
     */
    public void addIssue(Marking comic) {
        // get all meta objects from comic & create copy
        Comic comic_real = comic.getComic();
        Publisher pub = comic_real.getPublisher();
        Series series = comic_real.getSeries();
        Volume vol = comic_real.getVolume();
        Marking comic_copy = new Comic(comic_real);

        // see if meta objects exist within collection. if not, create copies of them
        Publisher pub_copy = collection.publisherExists(pub.getName()) ?
            collection.getPublisher(pub.getName()) :
            new Publisher(pub.getName());

        Series series_copy = pub_copy.seriesExists(series.getSeriesTitle()) ?
            pub.getSeries(series.getSeriesTitle()) :
            new Series(series.getSeriesTitle());

        Volume vol_copy = series_copy.volumeExists(vol.getVolumeNumber()) ?
            series.getVolume(vol.getVolumeNumber()) :
            new Volume(vol.getVolumeNumber());

        // add all items bottom up to the collection
        vol_copy.addIssue(comic_copy);
        series_copy.addVolume(vol_copy);
        pub_copy.addSeries(series_copy);
        collection.addPublisher(pub_copy);
    }

    /**
     * This removes a comic from
     * the personal collection,
     * then prunes the publisher
     * it belongs to.
     * 
     * This assumes the comic 
     * exists within the hierarchy
     * of this collection.
     * 
     * @param comic
     */
    public void delIssue(Marking comic) {
        Publisher pub = comic.getComic().getPublisher();
        comic.getComic().getVolume().delIssue(comic);
        prune(pub);
    }

    /**
     * This recursively enters each
     * tier of the hierarchy and 
     * prunes all ComicHolders that
     * have no issues from bottom
     * up to the top.
     * 
     * @param holder
     * @return void
     */
    public void prune(ComicHolder holder) {
        for (ComicHolder child : holder.getChildren()) {
            prune(child);
        }
        if (holder.getIssueCount() == 0) {
            holder.delSelf();
        }
    }
}
