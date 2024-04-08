package model.accounts;

import model.Comic;

public class UserAccount implements Account{

    private String username;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addComicToCollection'");
    }

    @Override
    public void removeComicFromCollection(Comic comic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeComicFromCollection'");
    }
    
    public String getUsername() {
        return username;
    }

}
