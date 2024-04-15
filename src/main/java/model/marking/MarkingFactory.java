package model.marking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Comic;

public class MarkingFactory {
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
                    // TODO: Add Signed comics
                    break;
                case 'A': 
                    // TODO: Add Auth comics
                    break;
            }
        }
        return comic;
    } 

    public static String getFormat(Marking comic) {
        List<String> format = new ArrayList<>();
            for (Marking mark : getMarkingOrder(comic)) {
                switch (mark.getClass().getName()) {
                    case "Grade":
                        format.add("G(" + ((Grade)mark).getGrade() + ")");
                        break;
                    case "Slab":
                         format.add("S");
                        break;
                    case "Auth": // TODO: Change on integration
                        // TODO: Add Auth Comics
                        format.add("A");
                        break;
                    case "Signed": // TODO: Change on Intergration
                        // TODO: Add Signed comics
                        break;
                    default:
                        break;
                }
            }
        return String.join(" ", format);
    }
}
