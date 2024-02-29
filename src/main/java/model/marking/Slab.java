package model.marking;

import java.math.BigDecimal;

/**
 * Concrete decorator class for marking a comic.
 */
public class Slab extends ComicDecorator{
    
    public Slab(Marking comic) {
        super(comic);
    }

    /**
     * The method in charge of calculating the new value of the comic after grading.
     * Multiplies the value of the comic by 2.
     */
    public BigDecimal getValue(){
        if(comic.getValue() == null){return null;}
        BigDecimal multiplier = new BigDecimal(2);
        BigDecimal newVal = comic.getValue().multiply(multiplier);
        return newVal;
    }

}