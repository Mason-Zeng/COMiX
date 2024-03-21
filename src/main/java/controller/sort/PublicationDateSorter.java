package controller.sort;

import java.time.LocalDate;
import java.util.Comparator;

import model.marking.Marking;

/**
 * PublicationDateSorter is an implementation of Comparator.
 * PublicationDateSorter in the Strategy pattern is a Concrete Strategy.
 * PublicationDateSorter implements an algorithm that sorts the data based on Publication Date.
 * 
 * @author Ayden Dazo
 */
public class PublicationDateSorter implements Comparator<Marking>{

    /**
     * The method that compares two comics, and returns an int based on which comes first.
     * Compares first by Publication Date
     * 
     * @param comic1 Any 1 comic.
     * @param comic2 Any 1 comic.
     * @return an int based on which comic has higher priority. Greater than 0 means that comic 1 goes first, less than 0 
     * means comic 2 goes first, and 0 means they are equal.
     */
    @Override
    public int compare(Marking comic1, Marking comic2) {
        LocalDate date1 = comic1.getDate();
        LocalDate date2 = comic2.getDate();

        return date1.compareTo(date2);
    }
    

    /**
     * A method to check equality. 
     * Any two instances of PublicationDateSorter are considered equal.
     * 
     * @param obj Another object that may or may not be a PublicationDateSorter
     * @return A boolean if the other object is a PublicationDateSorter
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PublicationDateSorter) ? true : false;
    }
}
