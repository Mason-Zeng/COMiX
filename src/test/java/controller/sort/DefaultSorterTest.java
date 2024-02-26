package controller.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import model.Comic;

public class DefaultSorterTest {
    
    @Test
    public void testCompareSeriesDifferent1(){
        //Setup
        int expected = 1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/*Series Title: Spider Man; 
                                whatever for the rest */);
        Comic comic2 = new Comic(/*Series Title: Incredible Hulk; 
                                whatever for the rest */);

        Comparator<Comic> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareSeriesDifferent2(){
        //Setup
        int expected = -1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/*Series Title: Incredible Mulk; 
                                whatever for the rest */);
        Comic comic2 = new Comic(/*Series Title: Incredible Hulk; 
                                whatever for the rest */);

        Comparator<Comic> comparer = new DefaultSorter();

        //Invoke
        int actual = comparer.compare(comic1, comic2);

        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareSameSeriesDiffVolume1(){
        //Setup
        int expected = 1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 1 */);
        Comic comic2 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 2 */);

        Comparator<Comic> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareSameSeriesDiffVolume2(){
        //Setup
        int expected = -1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 5 */);
        Comic comic2 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 3 */);

        Comparator<Comic> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareIssueNumberDiff1(){
        //Setup
        int expected = 1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 1
                                Issue Number: 5 */);
        Comic comic2 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 1
                                Issue Number: 28 */);

        Comparator<Comic> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareIssueNumberDiff2(){
            //Setup
            int expected = -1;

            //TODO fix the constructor when it is finished
            Comic comic1 = new Comic(/*Series Title: Incredible Hulk
                                    Volume Number: 1
                                    Issue Number: 9013 */);
            Comic comic2 = new Comic(/*Series Title: Incredible Hulk
                                    Volume Number: 1
                                    Issue Number: 2 */);
    
            Comparator<Comic> comparer = new DefaultSorter();
            
            //Invoke
            int actual = comparer.compare(comic1, comic2);
            
            //Analyze
            assertEquals(expected, actual);
    }

    @Test
    public void testCompareSameComic(){
        //Setup
        int expected = 0;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 1
                                Issue Number: 1 */);
        Comic comic2 = new Comic(/*Series Title: Incredible Hulk
                                Volume Number: 1
                                Issue Number: 1 */);

        Comparator<Comic> comparer = new DefaultSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }
}
