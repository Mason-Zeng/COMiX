package model.marking;

import java.util.ArrayList;
import java.util.Collections;

import model.Comic;

public class MarkingFactory {
    /**
     * Iteratively copies marking 
     * as well as all the markings 
     * that are owned by it.
     * @param marking
     * @return
     */
    public Marking copyMarking(Marking marking) {
        ArrayList<Marking> oldMarks = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            oldMarks.add(point);
            point = point.getMarking();
        }
        oldMarks.add(point);
        Collections.reverse(oldMarks);
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
        }
        return result;
    }
}
