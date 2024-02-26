package model.marking;

import java.math.BigDecimal;

/**
 * 
 */
public class Grade extends ComicDecorator{
    private int grade;

    public Grade(Marking comic, int grade) {
        super(comic);
        this.grade = grade;
    }

    public BigDecimal getValue(){
        if(grade == 1){
            BigDecimal multiplier = new BigDecimal(0.1);
            BigDecimal newVal = comic.getValue().multiply(multiplier);
            return newVal;
        }
        else if(grade >=2 && grade<= 10){
            BigDecimal multiplier = new BigDecimal(Math.log10(grade));
            BigDecimal newVal = comic.getValue().multiply(multiplier);
            return newVal;
        }
        return null;
    }


       
}

