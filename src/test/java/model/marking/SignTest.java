
package model.marking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.Comic;

public class SignTest {
    LocalDate date = LocalDate.of(2020, 1, 8);

    
    @Test
    public void checkSignCounter(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);

        //Invoke
        comic.getValue();
        int test = MarkingHandler.signCount(comic);
        int expected = 1;

        //Analyze
        assertEquals(expected, test);
    }
    
    
    @Test
    public void checkSignCounterMultiple(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);

        //Invoke
        comic.getValue();
        int test = MarkingHandler.signCount(comic);
        int expected = 5;

        //Analyze
        assertEquals(expected, test);
    }

    
    @Test
    public void checkSignCounterMultipleMarkings(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Grade(comic, 1);
        comic = new Sign(comic);
        comic = new Authenticate(comic);
        comic = new Slab(comic);
        
        
        //Invoke
        comic.getValue();
        int test = MarkingHandler.signCount(comic);
        int expected = 2;

        //Analyze
        assertEquals(expected, test);
    }
    
    @Test
    public void testOneSign(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("6.30");

        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testMultipleSign(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("6.95");

        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testSingleSignMarkings(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic= new Grade(comic, 1);
        comic= new Slab(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("1.26");

        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testMultipleSignMarkings(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic= new Grade(comic, 3);
        comic= new Slab(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("7.32");

        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testNull(){
        //Setup
        BigDecimal value = null;
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        

        //Invoke
        BigDecimal test = comic.getValue();

        //Analyze
        assertNull(test);
    }

    @Test
    public void testSlab(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Grade(comic, 2);
        comic = new Slab(comic);
        Exception thrownException = null;

        //Invoke
        try{
            comic = new Sign(comic);
        }
        catch
            (IllegalArgumentException e) {
                thrownException = e;
        }

        //Analyze
        assertNotNull(thrownException);
    }

    @Test
    public void testAuthenticate(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Authenticate(comic);
        Exception thrownException = null;
        
        //Invoke
        try{
            comic = new Sign(comic);
        }
        catch
            (IllegalArgumentException e) {
                thrownException = e;
        }

        //Analyze
        assertNotNull(thrownException);
    }

    @Test
    public void testValueSigns(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic= new Grade(comic, 3);
        comic = new Sign(comic);
        comic= new Slab(comic);

        //Invoke
        BigDecimal testValue = comic.getValue();
        BigDecimal expectedValue = new BigDecimal("7.68");
        int testSigns = MarkingHandler.signCount(comic);
        int expectedSigns = 6;

        //Analyze
        assertEquals(expectedValue, testValue);
        assertEquals(expectedSigns, testSigns); 
    }
}

