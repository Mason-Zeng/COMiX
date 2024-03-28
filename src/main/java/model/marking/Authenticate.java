package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;

import model.Comic;


public class Authenticate extends ComicDecorator{
    public Authenticate(Marking comic) {
        super(comic);
    }

    /**
     * The method in charge of calculating the new value of the comic after authentication.
     * Adds 20% to the value.
     */
    public BigDecimal getValue(){
        if(comic.getValue() == null || comic instanceof Authenticate || comic instanceof Slab || comic instanceof Comic){return null;}
        //Can comic be authenticated after graded and slabbed ?
        //If so, how to check if Signed. Put isSigned boolean field ?
        if(comic instanceof Sign){
            BigDecimal multiplier = new BigDecimal(1.20);
            BigDecimal newVal = comic.getValue().multiply(multiplier);
            newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
            return newVal;
        }
        return null;
    }
}
