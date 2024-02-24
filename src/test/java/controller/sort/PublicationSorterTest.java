package controller.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import model.Comic;

public class PublicationSorterTest {
    
    @Test
    public void testCompareDiffDates1(){
        //Setup
        int expected = 1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/* Feb 2nd 2002*/);
        Comic comic2 = new Comic(/* Feb 3rd 2005*/);

        Comparator<Comic> comparer = new PublicationDateSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareDiffDates2() {
        //Setup
        int expected = -1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/* Mar 2nd 2020*/);
        Comic comic2 = new Comic(/* Feb 3rd 2005*/);

        Comparator<Comic> comparer = new PublicationDateSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testSameDate() {
        //Setup
        int expected = 1;

        //TODO fix the constructor when it is finished
        Comic comic1 = new Comic(/* Apr 23rd 1994*/);
        Comic comic2 = new Comic(/* Apr 23rd 1994*/);

        Comparator<Comic> comparer = new PublicationDateSorter();
        
        //Invoke
        int actual = comparer.compare(comic1, comic2);
        
        //Analyze
        assertEquals(expected, actual);
    }
}
