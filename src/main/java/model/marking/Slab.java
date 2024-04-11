package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

import model.Comic;

/**
 * Concrete decorator class for slabbing a comic.
 */
public class Slab extends ComicDecorator{
    /**
     * Adds the Slab decorator onto a Marking
     * Cannot be applied if comic is not graded, or already slabbed, or if no markings applied
     * @param comic The comic that we are applying slab to
     */
    public Slab(Marking comic) {
        super(comic);
        if(isGrade(comic) == false || comic instanceof Slab || comic instanceof Comic){
            throw new IllegalArgumentException("Invalid comic type passed to Authenticate constructor");
        }
    }

    /**
     * The method in charge of calculating the new value of the comic after grading.
     * Multiplies the value of the comic by 2.
     */
    public BigDecimal getValue(){
        if(comic.getValue() == null){return null;}
        BigDecimal multiplier = new BigDecimal(2);
        BigDecimal newVal = comic.getValue().multiply(multiplier);
        newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
        return newVal;
    }
    
}
