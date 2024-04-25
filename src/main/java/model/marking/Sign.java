package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Concrete decorator class for slabbing a comic.
 */
public class Sign extends ComicDecorator{
    /**
     * Adds the Sign decorator onto a Marking.
     * Cannot be applied if comic is slabbed, or authenticated
     * @param comic The comic that we are applying grading to
     */
    public Sign(Marking comic) {
        super(comic);
        if(comic instanceof Slab || MarkingHandler.isAuthenticated(comic)){
            throw new IllegalArgumentException("Invalid comic type passed to Sign constructor");
        }
    }

    /**
     * The method in charge of calculating the new value of the comic after signing which can be done multiple times.
     * Adds 5% to the value.
     */
    public BigDecimal getValue(){
        if(comic.getValue() == null){return null;}
        BigDecimal multiplier = new BigDecimal(1.05);
        BigDecimal newVal = comic.getValue().multiply(multiplier);
        newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
        return newVal;
    }

    
}
