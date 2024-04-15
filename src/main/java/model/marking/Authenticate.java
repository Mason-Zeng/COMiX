package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import model.Comic;

/**
 * Concrete decorator class for slabbing a comic.
 */
public class Authenticate extends ComicDecorator{
    /**
     * Adds the Authenticate decorator onto a Marking.
     * Cannot be applied if comic is not signed, already authneticated, already slabbed, or no markings
     * @param comic The comic that we are applying authenticate to
     */
    public Authenticate(Marking comic) {
        super(comic);
        if (signCount(comic) == 0 || isAuthenticated(comic) || comic instanceof Slab || comic instanceof Comic) {
            throw new IllegalArgumentException("Invalid marking type passed to Authenticate constructor");
        }
    }

    /**
     * The method in charge of calculating the new value of the comic after authentication.
     * Adds 20% to the value.
     */
    public BigDecimal getValue(){
        if(comic.getValue() == null){return null;}
        BigDecimal multiplier = new BigDecimal(1.20);
        BigDecimal newVal = comic.getValue().multiply(multiplier);
        newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
        return newVal;
    }
}
