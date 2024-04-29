package model.history;

public interface Originator <T> {
    public Memento<T> createMemento();
    public void restore(Memento<T> memento);
}
