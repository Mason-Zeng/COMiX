package view;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import model.Comic;
import model.marking.Grade;
import model.marking.Marking;
import model.marking.Slab;

public class PTUI {
    @SuppressWarnings({ "resource" })
    public static void main(String[] args) 
    { 
       System.out.println("Welcome to Team 7 COMiX Application!");
       
       System.out.println("Press \"P\" for personal collection. Press \"D\" to see the database of comics");
       Scanner collection = new Scanner(System.in);
       String collectionInput = collection.nextLine();
       if(collectionInput.equals("D")){
            /*
             * Choose search algorithm
             * Add logic to search comic
             * Be able Add comic to personal collection
             */
       }

       else if(collectionInput.equals("P")){
            /*
             * Choose search algorithm to browse comics
             * Be able to add, remove comics
             * if want to grade, call markingSystem
             */
       }
       
    }

    
    @SuppressWarnings("unused")
    public BigDecimal markingSystem(){
        //DELETE THIS TEST DATA WHEN FULLY IMPLEMENTED
        BigDecimal value = new BigDecimal(6);
        LocalDate date = LocalDate.of(2020, 1, 8);
        Marking comic = new Comic("title", 3, "description", value, date);

        int flag = 0;
        while(flag == 0){
            System.out.println("Give the comment a grade. *Number from 1-10*");
            Scanner grade = new Scanner(System.in);
            Integer gradeInput = grade.nextInt();

            if(gradeInput == null){
                System.out.println("Please choose a number from 1-10");
            }
            else{
                comic = new Grade(comic, gradeInput);
                Scanner slab = new Scanner(System.in);
                String slabChoice = slab.nextLine();
                slabChoice= slabChoice.toUpperCase();
                
                int flag1 = 0;
                while(flag1 == 0){
                    System.out.println("Would you like to slab this comic? Yes(Y) or No(N)");
                    if(slabChoice.equals("N")){
                        return comic.getValue();
                    }
                    else if(slabChoice.equals("Y")){
                        comic = new Slab(comic);
                        return comic.getValue();
                    }
                    else{
                        System.out.println("Please either choose Yes(Y) or No(N)");
                    }
                }
            }
        }
        return null;
    }
}
