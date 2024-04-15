package controller;

import model.hierarchy.*;
import model.marking.Marking;
import model.marking.MarkingFactory;


public class CollectionManager {
    private Collection collection;

    public CollectionManager(Collection collection) {
        this.collection = collection;
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
        Publisher pub = comic.getPublisher();
        Series series = comic.getSeries();
        Volume vol = comic.getVolume();
        Marking comic_copy = MarkingFactory.copyMarking(comic);

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
        Publisher pub = comic.getPublisher();
        comic.getVolume().delIssue(comic);
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
