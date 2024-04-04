
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
        

        //Invoke
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic.getValue();
        comic.getValue();
        int count = comic.getSignCount();
        int expected = 5;

        //Analyze
        assertEquals(expected, count);
    }
}

