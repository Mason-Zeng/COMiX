package model.accounts;

import java.util.ArrayList;

import model.Comic;

public class UserAccount implements Account{

    private String username;
    private final ArrayList<Comic> comicCollection = new ArrayList<>();

    public UserAccount(String username){
        this.username = username;
    }

    @Override
    public void searchCollection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchCollection'");
    }

    @Override
    public void addComicToCollection(Comic comic) {
        comicCollection.add(comic);
    }

    @Override
    public void removeComicFromCollection(Comic comic) {
        comicCollection.remove(comic);
    }
    
    public String getUsername() {
        return username;
    }

    public ArrayList<Comic> getComics() {
        return comicCollection;
    }

}
