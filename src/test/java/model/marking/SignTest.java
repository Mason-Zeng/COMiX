
package model.marking;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Sign(comic);

        //Invoke
        comic.getValue();
        int count = comic.getSignCount();
        int expected = 1;

        //Analyze
        assertEquals(expected, count);
    }

    @Test
    public void checkSignCounterMultiple(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Sign(comic);
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic.getValue();
        
        //Invoke
        int count = comic.getSignCount();
        int expected = 5;

        //Analyze
        assertEquals(expected, count);
    }

    /* 
    @Test
    public void checkSignCounterMultipleMarkings(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Sign(comic);
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic = new Grade(comic, 1);
        comic = new Authenticate(comic);
        comic = new Slab(comic);
        BigDecimal test = comic.getValue();
        
        //Invoke
        int count = comic.getSignCount();
        int expected = 5;

        //Analyze
        assertEquals(expected, count);
    }
    */
    @Test
    public void testOneSign(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
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
        Marking comic = new Comic("title", 3, "description", value, date);
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
        Marking comic = new Comic("title", 3, "description", value, date);
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
        Marking comic = new Comic("title", 3, "description", value, date);
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
        Marking comic = new Comic("title", 3, "description", value, date);
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
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 2);
        comic = new Slab(comic);
        comic = new Sign(comic);

        //Invoke
        BigDecimal test = comic.getValue();

        //Analyze
        assertNull(test);
    }

    @Test
    public void testAuthenticate(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Sign(comic);
        comic = new Authenticate(comic);
        //comic = new Sign(comic);

        //Invoke
        BigDecimal test = comic.getValue();

        //Analyze
        assertNull(test);
    }
}

