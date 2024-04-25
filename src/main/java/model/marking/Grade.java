package model.marking;

import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 * Concrete decorator class for grading a comic.
 */
public class Grade extends ComicDecorator{
    //An integer from 1-10 that represents the grade of the comic
    private int grade;

    /**
     * Adds the Grade decorator onto a Marking.
     * Cannot be applied if comic is slabbed, or already graded
     * @param comic The comic that we are applying grading to
     */
    public Grade(Marking comic, int grade) {
        super(comic);
        this.grade = grade;
        if(MarkingHandler.isGrade(comic) || comic instanceof Slab){
            throw new IllegalArgumentException("Invalid marking type passed to Grade constructor");
        }
    }

    /*
     * The method in charge of calculating the new value of the comic after grading.
     * If the grade is 1, we multiply the value by 0.1
     * If the grade is 2-10, we multiply the value by log(grade)
     */
    public BigDecimal getValue(){
        if(comic.getValue()==null){return null;}
        if(grade == 1){
            BigDecimal multiplier = new BigDecimal(0.1);
            BigDecimal newVal = comic.getValue().multiply(multiplier);
            newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
            return newVal;
        }
        else if(grade >=2 && grade<= 10){
            BigDecimal multiplier = new BigDecimal(Math.log10(grade));
            BigDecimal newVal = comic.getValue().multiply(multiplier);
            newVal = newVal.setScale(2, RoundingMode.HALF_EVEN);
            return newVal;
        }
        return null;
    }
    
    public int getGrade() {
        return grade;
    }

}

