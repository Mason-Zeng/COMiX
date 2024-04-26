package model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CaretakerTest {

    public static class MockOriginator implements Originator<Object> {
        public Object state;

        public MockOriginator(Object obj) {
            state = obj;
        }
            
        @Override
        public Memento<Object> createMemento() {
            return new Memento<Object>(state);
        }

        @Override
        public void restore(Memento<Object> memento) {
            state = memento.getState();
        }
    }

    private static Object expected;
    private static MockOriginator mockOriginator;
    private static Caretaker<Object> caretaker;

    @BeforeAll
    public static void resetCaretaker() {
        expected = "expected";
        mockOriginator = new MockOriginator(expected);
        caretaker = new Caretaker<>(mockOriginator);
    }

    @Test
    public void testCaretakerUndo() {
        caretaker.capture();

        Object unexpected = "unexpected";

        mockOriginator.state = unexpected;

        caretaker.undo();

        assertEquals(expected, mockOriginator.state);
        assertNotEquals(unexpected, mockOriginator.state);
    }

    @Test
    public void testCaretakerRedo() {
        caretaker.capture();

        Object obj1 = "obj1";

        Object obj2 = "obj2";

        mockOriginator.state = obj1;

        caretaker.capture();

        mockOriginator.state = obj2;

        caretaker.undo();

        assertEquals(obj1, mockOriginator.state);

        caretaker.undo();

        assertEquals(expected, mockOriginator.state);

        caretaker.redo();

        assertEquals(obj1, mockOriginator.state);

        caretaker.redo();

        assertEquals(obj2, mockOriginator.state);

    }





}
