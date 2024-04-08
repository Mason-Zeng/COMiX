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
    public Marking copyMarking(Marking marking) {
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
        }
        return result;
    }

    public List<Marking> getMarkingOrder(Marking marking) {
        ArrayList<Marking> result = new ArrayList<>();
        Marking point = marking;
        while (!(point instanceof Comic)) {
            result.add(point);
            point = point.getMarking();
        }
        result.add(point);
        Collections.reverse(result);
        return result;
    }
}
