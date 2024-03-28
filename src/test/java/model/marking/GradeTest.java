package model.marking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.Comic;
public class GradeTest {
    LocalDate date = LocalDate.of(2020, 1, 8);

    @Test
    public void testMarkingGrade1(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 1);

        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("0.60"); // Adjusted to use string for precise BigDecimal creation
    
        //Analyze
        assertEquals(expected, test);
    }

    @Test
    public void testGradeGreaterThan1(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 4);

        //Invoke
        BigDecimal test = comic.getValue();
        BigDecimal expected = new BigDecimal("3.61");
        
        //Analyze
        assertEquals(test, expected);
    }

    @Test
    public void testInvalid(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        Marking comic = new Comic("title", 3, "description", value, date);
        comic = new Grade(comic, 0);
        
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
        comic = new Grade(comic, 25);
        
        //Invoke
        BigDecimal test = comic.getValue();
        
        //Analyze
        assertNull(test);
    }
}
