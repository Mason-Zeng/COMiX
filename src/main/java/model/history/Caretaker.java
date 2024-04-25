package model.history;

import java.util.ArrayList;
import java.util.List;

public class Caretaker <T> {
    
    private List<Memento<T>> states;
    private Originator<T> originator;
    private int point;

    public Caretaker(Originator<T> originator) {
        states = new ArrayList<>();
        point = 0;
    }

    public void capture() {
        states = states.subList(0, ++point);
        states.add(originator.createMemento());
    }
    
    public void undo() {
        if (point == 0) return;
        originator.restore(states.get(--point));
    }

    public void redo() {
        if (point == states.size() - 1) return;
        originator.restore(states.get(++point));
    }
}
