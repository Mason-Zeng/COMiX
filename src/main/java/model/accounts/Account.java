package model.accounts;

import java.util.List;

import model.Comic;
import model.marking.Marking;

public interface Account {
    public List<Marking> searchCollection(String searchStrategy, String sortStrategy, String query, String input);
    public void addComicToCollection(Comic comic);
    public void removeComicFromCollection(Comic comic);
}
