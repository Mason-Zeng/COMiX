package model.accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.ComicSearcher;
import controller.ForeignDataHandler;
import controller.search.ExactSearch;
import controller.search.PartialSearch;
import controller.sort.DefaultSorter;
import controller.sort.PublicationDateSorter;
import controller.sort.TrueDefaultSorter;
import controller.sort.IssueNumberSorter;
import model.history.Memento;
import model.history.Originator;
import model.marking.Marking;

public class UserAccount implements Account, Originator<List<Marking>>{

    private String username;
    private static File userFile;
    private List<Marking> COMICS = new ArrayList<>();
    private ForeignDataHandler dataHandler;

    public UserAccount(String username){
        String[] usernameTokens = username.split(" ");
        this.username = "";
        for (int i = 0; i < usernameTokens.length; i++) {
            this.username += usernameTokens[i].substring(0, 1).toUpperCase() + usernameTokens[i].substring(1).toLowerCase();
            if (i < usernameTokens.length - 1){
                this.username += " ";
            }
        }
        
        userFile = new File("data/users/" + this.username + ".json");
        importCollection(userFile, false);
    }
 
    public void importCollection(File file, boolean overwrite) {
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataHandler = ForeignDataHandler.getHandler();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (br.readLine() != null){
                COMICS = dataHandler.importData(file);
            }  
        }
            catch (IOException e) {
            e.printStackTrace();
        }

        if (overwrite) {
            exportCollection(userFile);
        }
    }

    public void exportCollection(File file) {
        try {
            dataHandler.exportData(file, COMICS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Marking> searchCollection(String searchStrategy, String sortStrategy, String query, String input) {
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

    @Override
    public void addComicToCollection(Marking comic) {
        COMICS.add(comic);
        exportCollection(userFile);
    }

    @Override
    public void removeComicFromCollection(Marking comic) {
        COMICS.remove(comic);
        exportCollection(userFile);
    }
    
    public String getUsername() {
        return username;
    }

    public List<Marking> getComics() {
        return COMICS;
    }

    @Override
    public Memento<List<Marking>> createMemento() {
        return new Memento<List<Marking>>(new ArrayList<>(COMICS));
    }

    @Override
    public void restore(Memento<List<Marking>> memento) {
        COMICS = new ArrayList<>(memento.getState());
    }

}
