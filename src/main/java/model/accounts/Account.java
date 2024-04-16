package model.accounts;

import java.io.File;
import java.util.List;

import model.marking.Marking;

public interface Account {
    public List<Marking> searchCollection(String searchStrategy, String sortStrategy, String query, String input);
    public void addComicToCollection(Marking comic);
    public void removeComicFromCollection(Marking comic);
    public void importCollection(File file, boolean overwrite);
    public void exportCollection(File file);
}
