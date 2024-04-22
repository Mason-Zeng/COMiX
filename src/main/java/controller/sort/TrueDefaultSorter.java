package controller.sort;

import java.util.Comparator;

import model.marking.Marking;

/**
 * TrueDefaultSorter is an implementation of Comparator.
 * TrueDefaultSorter in the Strategy pattern is a Concrete Strategy.
 * TrueDefaultSorter implements an algorithm that sorts the data based on Publisher Series Title, Volume Number, and Issue number.
 * TrueDefaultSorter is used when initializing a page but isn't available once a user does a search.
 * 
 * @author Ayden Dazo
 */
public class TrueDefaultSorter implements Comparator<Marking> {


    /**
     * This comparator is created to due to the 
     * fact that String comparison does not sort
     * in an intuitive manner. This exists to 
     * perform comparisons in a manner that makes
     * sense. 
     */
    private Comparator<String> alphNumComp = new Comparator<String>() {

        /**
         * Reverses the string and returns an 
         * integer to use for comparison.
         * @param str
         * @return Integer to sort with
         */
        private int getAsciiBounded(String str) {
            char[] chars = new StringBuilder().append(str).reverse().toString().toCharArray();
            int result = 0;
            for (int i = 0; i < chars.length; i++) {
                result += (int)chars[i] * Math.pow(2, i);
            }
            return result;
        }

        @Override
        public int compare(String arg0, String arg1) {
            return getAsciiBounded(arg0) - getAsciiBounded(arg1);
        }
        
    };

    /**
     * The method that compares two comics, and returns an int based on which comes first.
     * Compares first by Publisher, Series Title, then by Volume Number, and finally by Issue Number.
     * Only sorts Series Title if Publishers are equal, Volume Number if Series Title are equal, and Issue Number if Volume Number and Series Title are equal
     * 
     * @param comic1 Any 1 comic.
     * @param comic2 Any 1 comic.
     * @return an int based on which comic has higher priority. Greater than 0 means that comic 1 goes first, less than 0 
     * means comic 2 goes first, and 0 means they are equal.
     */
    @Override
    public int compare(Marking comic1, Marking comic2) {
        String pub1 = comic1.getPublisherName();
        String pub2 = comic2.getPublisherName();

        String title1 = comic1.getSeriesTitle();
        String title2 = comic2.getSeriesTitle();

        int publisherCompare = pub1.compareTo(pub2);
        int seriesCompare = title1.compareTo(title2);
        int volumeCompare = comic1.getVolumeNumber().compareTo(comic2.getVolumeNumber());
        int issueCompare = alphNumComp.compare(comic1.getIssueNumber(), comic2.getIssueNumber());
        
        if (publisherCompare == 0){
            if (seriesCompare == 0) {
                if (volumeCompare == 0) {
                    return issueCompare;
                }
                return volumeCompare;
            }
            return seriesCompare;
        }
        return publisherCompare;
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
        return (obj instanceof TrueDefaultSorter) ? true : false;
    }
    
}
