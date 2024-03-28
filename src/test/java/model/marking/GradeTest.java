package model.marking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
