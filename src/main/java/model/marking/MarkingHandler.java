package model.marking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Comic;

public class MarkingHandler {
    /**
     * Iteratively copies marking 
     * as well as all the markings 
     * that are owned by it.
     * @param marking
     * @return
     */
    public static Marking copyMarking(Marking marking) {
        List<Marking> oldMarks = getMarkingOrder(marking);
        Marking result = null;
        for (Marking oldMark : oldMarks) {
            if (oldMark instanceof Comic) {
                result = new Comic((Comic)oldMark);
            }
            else if (oldMark instanceof Slab) {
                result = new Slab(result);
            }
            else if (oldMark instanceof Grade) {
                result = new Grade(result, ((Grade)oldMark).getGrade());
            }
            else if (oldMark instanceof Sign){
                result = new Sign(result);
            }
            else if (oldMark instanceof Authenticate){
                result = new Authenticate(result);
            }
        }
        return result;
    }

    public static List<Marking> getMarkingOrder(Marking marking) {
        ArrayList<Marking> result = new ArrayList<>();
        Marking point = marking;
        while (point != null) {
            result.add(point);
            point = point.getMarking();
        }
        Collections.reverse(result);
        return result;
    }

    public static Marking formatComic(Marking comic, String format) {
        if (format.length() == 0) {
            return comic;
        }
        for (String marking : format.split(" ")) {
            switch (marking.charAt(0)) {
                case 'G':
                    Integer grade = Integer.parseInt(marking.substring(2, marking.length()-1));
                    comic = new Grade(comic, grade);
                    break;
                case 'S':
                    comic = new Slab(comic);
                    break;
                case 's':
                    comic = new Sign(comic);
                    break;
                case 'A': 
                    comic = new Authenticate(comic);
                    break;
            }
        }
        return comic;
    } 

    public static String getFormat(Marking comic) {
        List<String> format = new ArrayList<>();
            for (Marking mark : getMarkingOrder(comic)) {
                switch (mark.getClass().getSimpleName()) {
                    case "Grade":
                        format.add("G(" + ((Grade)mark).getGrade() + ")");
                        break;
                    case "Slab":
                         format.add("S");
                        break;
                    case "Authenticate":
                        format.add("A");
                        break;
                    case "Sign":
                        format.add("s");
                        break;
                    default:
                        break;
                }
            }
        return String.join(" ", format);
    }

    
    /*
     * Returns the number of times the object
     * was wrapped in Sign
     */
    public static int signCount(Marking marking){
        int count = 0;
        List<Marking> result = getMarkingOrder(marking);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Sign){
                count++;
            }
        }
        return count;
    }

    /*
     * Checks the marking to see if the object has 
     * been marked with Grade
     */
    public static boolean isGrade(Marking marking) {
        List<Marking> result = getMarkingOrder(marking);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Grade){
                return true;
            }
        }
        return false;
    }

        /*
     * Checks the marking to see if the object has 
     * been marked with Grade
     */
    public static int getGrade(Marking marking) {
        List<Marking> result = getMarkingOrder(marking);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Grade){
                Grade grade = (Grade) result.get(i);
                return grade.getGrade();
            }
        }
        return 0;
    }

    /*
     * Checks the marking to see if the object has 
     * been marked with Authenticate
     */
    public static boolean isAuthenticated(Marking marking) {
        List<Marking> result = getMarkingOrder(marking);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Authenticate){
                return true;
            }
        }
        return false;
    }

        /*
     * Checks the marking to see if the object has 
     * been marked with Slabbed
     */
    public static boolean isSlabbed(Marking marking) {
        List<Marking> result = getMarkingOrder(marking);
        for(int i=0; i<result.size(); i++){
            if(result.get(i) instanceof Slab){
                return true;
            }
        }
        return false;
    }
}
