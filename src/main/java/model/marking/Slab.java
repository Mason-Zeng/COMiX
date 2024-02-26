package model.marking;

import java.math.BigDecimal;


public class Slab extends ComicDecorator{
    
    public Slab(Marking comic) {
        super(comic);
    }

    public BigDecimal getValue(){
        BigDecimal multiplier = new BigDecimal(2);
        BigDecimal newVal = comic.getValue().multiply(multiplier);
        return newVal;
    }

}
