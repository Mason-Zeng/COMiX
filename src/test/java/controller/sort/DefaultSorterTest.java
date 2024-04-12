package controller.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import org.junit.jupiter.api.Test;


import model.hierarchy.Series;
import model.hierarchy.Volume;
import model.marking.Marking;
import model.Comic;

public class DefaultSorterTest {

    @Test
    public void testCompareSeriesDifferent1(){
        //Setup

        Comic comic1 = new Comic("Spider Man", "1", "Amazing and a Spider!", 
                                new BigDecimal("2.00"), LocalDate.parse("2022-01-07"), new Volume("2", new Series("Spider-Man")));
        Comic comic2 = new Comic("Incredible Hulk", "1", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("2", new Series("Incredible Hulk")));

        Comparator<Marking> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual > 0);
    }

    @Test
    public void testCompareSeriesDifferent2(){
        //Setup
        Comic comic1 = new Comic("Incredible Bulk", "1", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("2", new Series("The Incredible Bulk")));
        Comic comic2 = new Comic("Incredible Hulk", "1", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("2", new Series("The Incredible Hulk")));

        Comparator<Marking> comparer = new DefaultSorter();

        //Invoke
        int actual = comparer.compare(comic1, comic2);

        //Analyze
        assertTrue(actual < 0);
    }

    @Test
    public void testCompareSameSeriesDiffVolume1(){
        //Setup
        Comic comic1 = new Comic("Incredible Hulk", "5", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));
        Comic comic2 = new Comic("Incredible Hulk", "1", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("2", new Series("The Incredible Hulk")));

        Comparator<Marking> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual < 0);
    }

    @Test
    public void testCompareSameSeriesDiffVolume2(){
        //Setup
        Comic comic1 = new Comic("Incredible Hulk", "5", "Super strong, and green!", 
                                    new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("5", new Series("The Incredible Hulk")));
        Comic comic2 = new Comic("Incredible Hulk", "5", "Super strong, and green!", 
                            new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("3", new Series("The Incredible Hulk")));

        Comparator<Marking> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual > 0);
    }

    @Test
    public void testCompareIssueNumberDiff1(){
        //Setup
        Comic comic1 = new Comic("Incredible Hulk", "5", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));
        Comic comic2 = new Comic("Incredible Hulk", "28", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));

        Comparator<Marking> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertTrue(actual < 0);
    }

    @Test
    public void testCompareIssueNumberDiff2(){
            //Setup
            Comic comic1 = new Comic("Incredible Hulk", "9013", "Super strong, and green!", 
                                    new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));
            Comic comic2 = new Comic("Incredible Hulk", "2", "Super strong, and green!", 
                                    new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));
    
            Comparator<Marking> comparer = new DefaultSorter();
            
            //Invoke
            int actual = comparer.compare(comic1, comic2);
            
            //Analyze
            assertTrue(actual > 0);
    }

    @Test
    public void testCompareSameComic(){
        //Setup
        int expected = 0;

        Comic comic1 = new Comic("Incredible Hulk", "5", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));
        Comic comic2 = new Comic("Incredible Hulk", "5", "Super strong, and green!", 
                                new BigDecimal("5.00"), LocalDate.parse("2022-01-07"), new Volume("1", new Series("The Incredible Hulk")));

        Comparator<Marking> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }
}
