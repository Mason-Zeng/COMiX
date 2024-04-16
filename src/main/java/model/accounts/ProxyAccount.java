package model.accounts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import controller.ComicSearcher;
import controller.ForeignDataHandler;
import controller.search.ExactSearch;
import controller.search.PartialSearch;
import controller.sort.DefaultSorter;
import controller.sort.IssueNumberSorter;
import controller.sort.PublicationDateSorter;
import model.Comic;
import model.marking.Marking;

public class ProxyAccount implements Account{

    private UserAccount userAccount;
    private static final String FILE = "data/comics.json"; 
    private static List<Marking> COMICS;
    private ForeignDataHandler dataHandler;

    public ProxyAccount(){
        dataHandler = ForeignDataHandler.getHandler();
        try {
            COMICS = dataHandler.importData(new File(FILE));
        }
            catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Marking> searchDatabase(String searchStrategy, String sortStrategy, String query, String input){
        ComicSearcher searcher = new ComicSearcher(COMICS);
        searcher.setSearcher((searchStrategy == "Partial Search") ? new PartialSearch() : new ExactSearch());
        if (sortStrategy.equals("Sort By Default")){
            searcher.setSorter(new DefaultSorter());
        }
        else if (sortStrategy.equals("Sort By Date")){
            searcher.setSorter(new PublicationDateSorter());
        }
        else {
            searcher.setSorter(new IssueNumberSorter());
        }
        
        input = input.toLowerCase().replace(" ", "_");
        return searcher.search(query, input);
    }

    public void login(String username){
        this.userAccount = new UserAccount(username);
    }

    public void logout(){
        this.userAccount = null;
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
