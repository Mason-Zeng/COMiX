package view;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import model.Comic;
import model.marking.Marking;

public class PTUI {
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
    
    public BigDecimal markingSystem(){
        //DELETE THIS TEST DATA WHEN FULLY IMPLEMENTED
        BigDecimal value = new BigDecimal(6);
        LocalDate date = LocalDate.of(2020, 1, 8);
        Marking comic = new Comic("title", 3, "description", value, date);

        int flag = 0;
        while(flag == 0){
            System.out.println("Give the comment a grade. *Number from 1-10*");
            Scanner grade = new Scanner(System.in);
            int gradeInput = grade.nextInt();
            
        }
        return null;
    }
}
