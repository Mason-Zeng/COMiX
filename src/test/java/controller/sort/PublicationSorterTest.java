package controller.sort;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import controller.Comic;
import controller.hierarchy.Volume;

public class PublicationSorterTest {
    
    @Test
    public void testCompareDiffDates1(){
        //Setup
        Comic comic1 = new Comic("Incredible Hulk", 5, "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2002-02-02"), new Volume(1));
        Comic comic2 = new Comic("Incredible Hulk", 5, "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2005-02-03"), new Volume(1));

        Comparator<Comic> comparer = new PublicationDateSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual < 0);
    }

    @Test
    public void testCompareDiffDates2() {
        //Setup
        Comic comic1 = new Comic("Incredible Hulk", 5, "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2020-03-02"), new Volume(1));
        Comic comic2 = new Comic("Incredible Hulk", 5, "Super strong, and green!", 
        new BigDecimal("5.00"), LocalDate.parse("2005-02-03"), new Volume(1));

        Comparator<Comic> comparer = new PublicationDateSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual > 0);
    }

    @Test
    public void testSameDate() {
        //Setup
        Comic comic1 = new Comic("Incredible Hulk", 5, "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("1994-04-23"), new Volume(1));
        Comic comic2 = new Comic("Incredible Hulk", 5, "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("1994-04-23"), new Volume(1));

        Comparator<Comic> comparer = new PublicationDateSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual == 0);
    }
}
