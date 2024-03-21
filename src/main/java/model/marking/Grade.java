package model.marking;

import java.math.BigDecimal;

/**
 * Concrete decorator class for grading a comic.
 */
public class Grade extends ComicDecorator{
    //An integer from 1-10 that represents the grade of the comic
    private int grade;

    public Grade(Marking comic, int grade) {
        super(comic);
        this.grade = grade;
    }

    /*
     * The method in charge of calculating the new value of the comic after grading.
     * If the grade is 1, we multiply the value by 0.1
     * If the grade is 2-10, we multiply the value by log(grade)
     */
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
    
    public int getGrade() {
        return grade;
    }
}

