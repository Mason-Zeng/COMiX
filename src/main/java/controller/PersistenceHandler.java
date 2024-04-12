package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.hierarchy.Collection;
import model.marking.Marking;

public class PersistenceHandler {

    public static final String savePath = ".data/users/";
    
    private static PersistenceHandler handler;

    private PersistenceHandler() {}

    public static PersistenceHandler getPersistenceHandler() {
        if (handler == null) {
            handler = new PersistenceHandler();
        }
        return handler;
    }

    public void saveUser(Collection collection) throws IOException {
        String destination = savePath + collection.getUser() + ".json";
        File file = new File(destination);
        ForeignDataHandler fdhandler = ForeignDataHandler.getHandler();
        fdhandler.exportData(file, collection.getIssues());
    }

    public Collection loadUser(String name) throws IOException {
        String destination = savePath + name + ".json";
        File file = new File(destination);
        ForeignDataHandler fdhandler = ForeignDataHandler.getHandler();
        List<Marking> comics = fdhandler.importData(file);

        Collection result = new Collection(name);
        CollectionManager colManager = new CollectionManager(result);

        for (Marking mark : comics) {
            colManager.addIssue(mark);
        }
        return result;
    }

}
