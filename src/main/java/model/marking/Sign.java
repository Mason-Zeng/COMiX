package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Sign extends ComicDecorator{
    public Sign(Marking comic) {
        super(comic);
    }

    /**
     * The method in charge of calculating the new value of the comic after signing which can be done multiple times.
     * Adds 5% to the value.
     */
    public BigDecimal getValue(){
        if(comic.getValue() == null || comic instanceof Slab || comic instanceof Authenticate){return null;}
        BigDecimal multiplier = new BigDecimal(1.05);
        BigDecimal newVal = comic.getValue().multiply(multiplier);
        newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
        signCount++;
        return newVal;
    }
}
