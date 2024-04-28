package controller.search;
import java.util.List;
 
import model.marking.Marking;

/**
 * SpecificSearch is an interface that allows for polymorphism.
 * SpecificSearch in the Strategy pattern is a Strategy.
 * Concrete implemenations of SpecificSearch will contain algorithms for specific types of searching such as series title, issue number, etc.
 * 
 * @author Mason Zeng
 */
public interface SpecificSearch {
    /**
     * A method that will contain algorithms for searching in a concrete implementation
     * 
     * @param type Either partial or exact search
     * @param data The List of Comics
     * @param query What should be searched for
     * @return List of Comics that were found in the search
     */
    public List<Marking> searchData(String type, List<Marking> data, String query);
}
