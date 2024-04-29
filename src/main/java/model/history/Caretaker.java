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

    public void capture() {
        states.add(++point, originator.createMemento());
        if (!(states.size() - 1 == point)) {
            states = states.subList(0, point+1);
        }
    }
    
    public void undo() {
        if (point < 0) return;
        if (point == states.size() - 1) {
            states.add(++point, originator.createMemento());
        } else {
            states.set(point, originator.createMemento());        
        }
        originator.restore(states.get(--point));
    }

    public void redo() {
        if (point == states.size() - 1) return;
        originator.restore(states.get(++point));
        if (point == states.size() - 1) states.remove(point--);
    }
}
