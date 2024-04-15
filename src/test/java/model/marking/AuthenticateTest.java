package model.marking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.Comic;

public class AuthenticateTest {
    LocalDate date = LocalDate.of(2020, 1, 8);

    @Test
    public void testCheckSignedTrue(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        Exception thrownException = null;
        try{      
            comic = new Authenticate(comic);
        }
        catch
            (IllegalArgumentException e) {
                thrownException = e;
            }

        //Analyze
        assertNull(thrownException);
    }

    @Test
    public void testCheckSignedFalse(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Grade(comic, 4);
        comic = new Slab(comic);
        Exception thrownException = null;

        //Invoke
        try{      
            comic = new Authenticate(comic);
        }
        catch
            (IllegalArgumentException e) {
                thrownException = e;
            }

        //Analyze
        assertNotNull(thrownException);
    }
    @Test
    public void testOneSign(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Authenticate(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("7.56");
        
        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testMultipleSigns(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Sign(comic);
        comic = new Authenticate(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("8.34");
        
        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testNull(){
        //Setup
        BigDecimal value = null;
        Marking comic = new Comic("title", "3", "description", value, date);
        comic = new Sign(comic);
        comic = new Authenticate(comic);

        //Invoke
        BigDecimal test = comic.getValue();

        //Analyze
        assertNull(test);
    }

    @Test
    public void testComic(){
        //Setup
        BigDecimal value = null;
        Marking comic = new Comic("title", "3", "description", value, date);
        
        Exception thrownException = null;
        try{      
            comic = new Authenticate(comic);
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
        BigDecimal value = null;
        Marking comic = new Comic("title", "3", "description", value, date);
        Exception thrownException = null;

        //Invoke
        try{
            comic = new Sign(comic);
            comic = new Authenticate(comic);
            comic = new Authenticate(comic);
            comic = new Grade(comic, 4);
        }
        catch
            (IllegalArgumentException e) {
                thrownException = e;
            }

        //Analyze
        assertNotNull(thrownException);
    }

    @Test
    public void testSlab(){
        //Setup
        BigDecimal value = null;
        Marking comic = new Comic("title", "3", "description", value, date);
        Exception thrownException = null;
        
        //Invoke
        try{
            comic = new Sign(comic);
            comic = new Grade(comic, 5);
            comic = new Slab(comic);
            comic = new Authenticate(comic);
        }
        catch
            (IllegalArgumentException e) {
                thrownException = e;
            }

        //Analyze
        assertNotNull(thrownException);
    }

    
}
