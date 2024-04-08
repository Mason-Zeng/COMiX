package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

import model.Comic;

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
        if(comic.getValue() == null || comic instanceof Slab || comic instanceof Comic){return null;}
        if(isGrade(comic)){
            BigDecimal multiplier = new BigDecimal(2);
            BigDecimal newVal = comic.getValue().multiply(multiplier);
            newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
            return newVal;
        }
        return null;
    }

    public boolean isGrade(Marking marking) {
        ArrayList<Marking> result = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            result.add(point);
            point = point.getMarking();
        }
        result.add(point);
        Collections.reverse(result);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Grade){
                return true;
            }
        }
        return false;
    }
}
