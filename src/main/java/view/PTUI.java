package view;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import controller.CollectionManager;
import model.Comic;
import model.ComicOutput;
import model.marking.*;
import model.hierarchy.Collection;


public class PTUI {

    private static final String csvFile = "data/comics.csv"; 
    private static Scanner scan;
    private static Collection collection = new Collection("User");
    private static CollectionManager collectionManager = new CollectionManager(collection);

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        System.out.println("Welcome to Team 7 COMiX Application!");
        mainMenu();
        scan.close();
    }

    public static void mainMenu() {
        System.out.println("Press \"P\" for personal collection.\nPress \"D\" to see the database of comics.\nPress \"Q\" to quit");
        System.out.print(">> ");
        String collectionInput = scan.nextLine();
        collectionInput = collectionInput.toUpperCase();
        if(collectionInput.equals("D")){
            /*
             * Someone Implement (Most likely Aydan?)
             * Choose search algorithm
             * Add logic to search comic
             * Be able Add comic to personal collection
             */
            database();
        }
        else if(collectionInput.equals("P")){
            /*
            * Someone Implement (Most likely Swampnil)
            * Choose search algorithm to browse comics
            * Be able to add, remove comics
            * if want to grade, call markingSystem
            */
            personalCollection();
        } else if (collectionInput.equals("Q")) {
            System.out.println("Do ");
            return;
        }
        mainMenu();
    }

    public static void database() {
        List<ComicOutput> comics = ComicOutput.loadFromCSV(csvFile);
        System.out.print("");
    }

    public static void personalCollection() {
        int comic_count = collection.getIssueCount();
        System.out.println("You currently have x comics in your collection.");
    }

    // //Logic for a user wanting to grade a comic
    // String wantGradeChoice = scan.nextLine();
    // wantGradeChoice= wantGradeChoice.toUpperCase();
    
    // boolean continueLoop = true;
    // while(continueLoop){
    //     System.out.println("Would you like to grade this comic? Yes(Y) or No(N)");
    //     if(wantGradeChoice.equals("N")){
    //         continueLoop = false;
    //     }
    //     else if(wantGradeChoice.equals("Y")){
    //         markingSystem();
    //         continueLoop = false;
    //     }
    //     else{
    //         System.out.println("Please either choose Yes(Y) or No(N)");
    //     }
    // }

    
    @SuppressWarnings("unused")
    public static BigDecimal markingSystem(){
        //DELETE THIS TEST DATA WHEN FULLY IMPLEMENTED
        BigDecimal value = new BigDecimal(6);
        LocalDate date = LocalDate.of(2020, 1, 8);
        Marking comic = new Comic("title", 3, "description", value, date);
        
        try{
            int flag = 0;
            while(flag == 0){
                System.out.println("Give the comic a grade. *Number from 1-10*");
                Integer gradeInput = scan.nextInt();

                if(gradeInput == null){
                    System.out.println("Please choose a number from 1-10");
                }
                else{
                    comic = new Grade(comic, gradeInput);
                    String wantGradeChoice = scan.nextLine();
                    wantGradeChoice= wantGradeChoice.toUpperCase();
                    
                    int flag1 = 0;
                    while(flag1 == 0){
                        System.out.println("Would you like to slab this comic? Yes(Y) or No(N)");
                        if(wantGradeChoice.equals("N")){
                            return comic.getValue();
                        }
                        else if(wantGradeChoice.equals("Y")){
                            comic = new Slab(comic);
                            return comic.getValue();
                        }
                        else{
                            System.out.println("Please either choose Yes(Y) or No(N)");
                        }
                    }
                }
            }
        }

        catch(InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid input");
        }
        
        return null;
    }
}
