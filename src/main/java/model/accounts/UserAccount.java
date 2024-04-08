package model.accounts;

import java.util.ArrayList;
import java.util.List;

import controller.ComicSearcher;
import controller.search.ExactSearch;
import controller.search.PartialSearch;
import controller.sort.DefaultSorter;
import controller.sort.PublicationDateSorter;
import model.Comic;
import model.marking.Marking;

public class UserAccount implements Account{

    private String username;
    //File once added will be "data/users/%s", username
    private List<Marking> COMICS = new ArrayList<>();

    public UserAccount(String username){
        this.username = username;
    }

    @Override
    public List<Marking> searchCollection(String searchStrategy, String sortStrategy, String query, String input) {
        ComicSearcher searcher = new ComicSearcher(COMICS);
        searcher.setSearcher((searchStrategy == "Partial Search") ? new PartialSearch() : new ExactSearch());
        searcher.setSorter((sortStrategy == "Sort By Default") ? new DefaultSorter() : new PublicationDateSorter());
        input = input.toLowerCase().replace(" ", "_");
        return searcher.search(query, input);
    }

    @Override
    public void addComicToCollection(Comic comic) {
        COMICS.add(comic);
    }

    @Override
    public void removeComicFromCollection(Comic comic) {
        COMICS.remove(comic);
    }
    
    public String getUsername() {
        return username;
    }

    public List<Marking> getComics() {
        return COMICS;
    }

}
