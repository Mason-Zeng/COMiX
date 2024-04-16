package controller.sort;
import java.util.Comparator;
import model.marking.Marking;
/**
 * IssueNumberSorter is an implementation of Comparator.
 * IssueNumberSorter in the Strategy pattern is a Concrete Strategy.
 * IssueNumberSorter implements an algorithm that sorts the data based on numeric values of IssueNumber
 * 
 * @author Mason Zeng
 */
public class IssueNumberSorter implements Comparator<Marking>{

    @Override
    public int compare(Marking comic1, Marking comic2) {
    
        double value1 = comic1.extractIssueValue();
        double value2 = comic2.extractIssueValue();

        return Double.compare(value1, value2);
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
        return (obj instanceof IssueNumberSorter) ? true : false;
    }
}

