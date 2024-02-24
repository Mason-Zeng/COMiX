package controller.marking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import controller.Comic;

public class MarkingTest{
    @Test
    public void testMarkingGrade1(){
        //Setup
        BigDecimal value = new BigDecimal(6);
        MarkImplement comic = new Comic(value);
        comic = new Grade(comic, 1);
        comic = new Slab(comic);
        
        //Invoke
        BigDecimal test = comic.getValue();
        test = test.setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal expected = new BigDecimal(1.20);
        expected = expected.setScale(2, RoundingMode.HALF_EVEN);
        
        //Analyze
        assertEquals(expected, test);
    }
}