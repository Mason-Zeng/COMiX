package model.accounts;

import model.Comic;

public interface Account {
    public void searchCollection();
    public void addComicToCollection(Comic comic);
    public void removeComicFromCollection(Comic comic);
}
