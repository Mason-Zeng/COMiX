package model.history;

import java.util.ArrayList;
import java.util.List;

public class Caretaker <T> {
    
    private List<Memento<T>> states;
    private Originator<T> originator;
    private int point;

    public Caretaker(Originator<T> originator) {
        this.originator = originator;
        states = new ArrayList<>();
        point = -1;
    }

    private void rebuildList() {
        if (!(states.size() - 1 == point)) {
            states = states.subList(0, point+1);
        }
    }

    public void capture() {
        states.add(++point, originator.createMemento());
        rebuildList();
    }
    
    public void undo() {
        System.out.println("undo start " + point);
        if (point < 0) return;
        if (point == states.size() - 1) {
            states.add(++point, originator.createMemento());
        } else {
            states.set(point, originator.createMemento());        
        }
        originator.restore(states.get(--point));
        System.out.println("undo end " + point);
    }

    public void redo() {
        System.out.println("redo start " + point);
        if (point == states.size() - 1) return;
        originator.restore(states.get(++point));
        System.out.println("redo end " + point);
    }
}
