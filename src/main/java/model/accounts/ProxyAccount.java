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
import controller.sort.TrueDefaultSorter;
import model.history.Caretaker;
import model.marking.Marking;

public class ProxyAccount implements Account {

    private static final String FILE = "data/comics.json"; 
    private static List<Marking> COMICS;
    private ForeignDataHandler dataHandler;
    private UserAccount userAccount;
    private Caretaker<List<Marking>> collectionCaretaker;

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
        else if (sortStrategy.equals("True Default Sort")){
            searcher.setSorter(new TrueDefaultSorter());
        }
        else {
            searcher.setSorter(new IssueNumberSorter());
        }
        
        input = input.toLowerCase().replace(" ", "_");
        return searcher.search(query, input);
    }

    public void login(String username){
        this.userAccount = new UserAccount(username);
        this.collectionCaretaker = new Caretaker<List<Marking>>(userAccount);
    }

    public void logout(){
        this.userAccount = null;
        this.collectionCaretaker = null;
    }

    @Override
    public List<Marking> searchCollection(String searchStrategy, String sortStrategy, String query, String input) {
        if (this.userAccount == null){
            return null;
        }
        return userAccount.searchCollection(searchStrategy, sortStrategy, query, input);
    }

    @Override
    public void addComicToCollection(Marking comic) {
        if (this.userAccount == null){
            return;
        }
        collectionCaretaker.capture();
        userAccount.addComicToCollection(comic);
    }

    @Override
    public void removeComicFromCollection(Marking comic) {
        if (this.userAccount == null){
            return;
        }
        collectionCaretaker.capture();
        userAccount.removeComicFromCollection(comic);
    }

    public String getUsername(){
        if (this.userAccount == null){
            return "Guest";
        }
        return userAccount.getUsername();
    }

    @Override
    public void importCollection(File file, boolean overwrite) {
        if (this.userAccount == null) {
            return;
        }
        userAccount.importCollection(file, overwrite);
    }

    @Override
    public void exportCollection(File file) {
        if (this.userAccount == null) {
            return;
        }
        userAccount.exportCollection(file);
    }
    
    public void undo() {
        if (collectionCaretaker != null) collectionCaretaker.undo();
    }

    public void redo() {
        if (collectionCaretaker != null) collectionCaretaker.redo();
    }
}
