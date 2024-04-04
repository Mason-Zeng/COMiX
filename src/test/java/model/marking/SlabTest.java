package model.marking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.Comic;

public class SlabTest{
    LocalDate date = LocalDate.of(2020, 1, 8);


    @Test
    public void testGrade1(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 1);
        comic = new Slab(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("1.20"); // Adjusted to use string for precise BigDecimal creation
    
        //Analyze
        assertEquals(test, expected);
    }

    @Test
    public void testGradeGreaterThan1(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 4);
        comic = new Slab(comic);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("7.22");
        
        //Analyze
        assertEquals(test, expected);
    }

    @Test
    public void testInvalid(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 0);
        comic = new Slab(comic);
        
        //Invoke
        BigDecimal test = comic.getValue();
        
        //Analyze
        assertNull(test);
    }

    @Test
    public void testMarkingInvalidTypeSlab(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 5);
        comic = new Slab(comic);
        comic = new Slab(comic);
        
        //Invoke
        BigDecimal test = comic.getValue();
        
        //Analyze
        assertNull(test);
    }

    @Test
    public void testMarkingInvalidTypeComic(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Slab(comic);
        
        //Invoke
        BigDecimal test = comic.getValue();
        
        //Analyze
        assertNull(test);
    }

    @Test
    public void testNullValue(){
        //Setup
        BigDecimal value = null;
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 6);
        comic = new Slab(comic);
        
        //Invoke
        BigDecimal test = comic.getValue();
        
        //Analyze
        assertNull(test);
    }
    
}