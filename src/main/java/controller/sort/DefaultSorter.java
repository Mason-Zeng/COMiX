package controller.sort;

import java.util.Comparator;

import model.marking.Marking;

/**
 * DefaultSorter is an implementation of Comparator.
 * DefaultSorter in the Strategy pattern is a Concrete Strategy.
 * DefaultSorter implements an algorithm that sorts the data based on Series Title, Volume Number, and Issue number.
 * 
 * @author Ayden Dazo
 */
public class DefaultSorter implements Comparator<Marking> {


    /**
     * The method that compares two comics, and returns an int based on which comes first.
     * Compares first by Series Title, then by Volume Number, and finally by Issue Number.
     * Only sorts Volume Number if Series Title are equal, and Issue Number if Volume Number and Series Title are equal
     * 
     * @param comic1 Any 1 comic.
     * @param comic2 Any 1 comic.
     * @return an int based on which comic has higher priority. Greater than 0 means that comic 1 goes first, less than 0 
     * means comic 2 goes first, and 0 means they are equal.
     */
    @Override
    public int compare(Marking comic1, Marking comic2) {
        String title1 = comic1.getSeriesTitle();
        String title2 = comic2.getSeriesTitle();

        int seriesCompare = title1.compareTo(title2);
        int volumeCompare = comic1.getVolumeNumber() - comic2.getVolumeNumber();
        int issueCompare = comic1.getIssueNumber() -  comic2.getIssueNumber();
        
        if (seriesCompare == 0) {
            if (volumeCompare == 0) {
                return issueCompare;
            }
            return volumeCompare;
        }
        return seriesCompare;
    }

        /**
     * A method to check equality. 
     * Any two instances of DefaultSorter are considered equal.
     * 
     * @param obj Another object that may or may not be a DefaultSorter
     * @return A boolean if the other object is a DefaultSorter
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DefaultSorter) ? true : false;
    }
    
}
