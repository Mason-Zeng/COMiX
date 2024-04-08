package model.accounts;

import java.util.Comparator;
import java.util.List;

import controller.ComicSearcher;
import controller.search.ExactSearch;
import controller.search.PartialSearch;
import controller.search.Searcher;
import controller.sort.DefaultSorter;
import controller.sort.PublicationDateSorter;
import model.Comic;
import model.ComicOutput;
import model.marking.Marking;

public class ProxyAccount implements Account{

    private UserAccount userAccount;
    private static final String csvFile = "data/comics.csv"; 
    // private static final List<Marking> COMICS = ComicOutput.loadFromCSV(csvFile)
    //     .stream()
    //     .map(Comic::new)
    //     .map(Marking.class::cast)
    //     .toList();

    public void searchDatabase(String searchStrategy, String sortStrategy, String query, String input){
        // ComicSearcher searcher = new ComicSearcher(COMICS);
        // searcher.setSearcher((searchStrategy == "Partial Search") ? new PartialSearch() : new ExactSearch());
        // searcher.setSorter((sortStrategy == "Sort By Default") ? new DefaultSorter() : new PublicationDateSorter());
        // input = input.toLowerCase().replace(" ", "_");
        // System.out.println(searcher.search(query, input));
    }

    public void login(String username){
        this.userAccount = new UserAccount(username);
    }

    @Override
    public List<Marking> searchCollection(String searchStrategy, String sortStrategy, String query, String input) {
        if (this.userAccount == null){
            return null;
        }
        return userAccount.searchCollection(searchStrategy, sortStrategy, query, input);
    }

    @Override
    public void addComicToCollection(Comic comic) {
        if (this.userAccount == null){
            return;
        }
        userAccount.addComicToCollection(comic);
    }

    @Override
    public void removeComicFromCollection(Comic comic) {
        if (this.userAccount == null){
            return;
        }
        userAccount.removeComicFromCollection(comic);
    }

    public String getUsername(){
        if (this.userAccount == null){
            return "Guest";
        }
        return userAccount.getUsername();
    }
    
}
