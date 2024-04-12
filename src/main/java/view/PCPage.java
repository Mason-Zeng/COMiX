package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import model.accounts.ProxyAccount;
import model.marking.Marking;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PCPage extends Application {
    private final Collection<Node> nodes = new ArrayList<>();
    private ProxyAccount proxyAccount;
    private final String searchers[] = {"Partial Search", "Exact Search"};
    private final String sorters[] = {"Sort By Default", "Sort By Date"};
    private final String searchOptions[] = {"Series Title", "Issue Number", "Story Title", "Publisher", "Creator", "Date"/*, "Graded", "Slabbed", "Signed", "Authenticated", "Runs", "Gaps" */};
    private List<Marking> COMICS;
    private int comicCounter = 1; 
    private Collection<Button> prevButtons;

    public PCPage(ProxyAccount proxyAccount){
        this.proxyAccount = proxyAccount;
        COMICS = proxyAccount.searchCollection("Partial Search", "Sort By Default", "", "Series Title");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        GridPane gridPane = new GridPane();
        Group group = new Group();

        nodes.add(gridPane);
        nodes.add(group);

        Rectangle rect = new Rectangle(1000, 70, Color.LIGHTGRAY);

        Label username = new Label("Hello, " + proxyAccount.getUsername());
        username.setFont(Font.font(null, FontWeight.BOLD, (proxyAccount.getUsername().length() > 14) ? 20 : 25));
        username.setMinHeight(70);
        gridPane.add(username, 0, 0);

        Label undoLabel = new Label("↺");
        undoLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        undoLabel.setMinHeight(70);

        Button undoButton = new Button("", undoLabel);
        undoButton.setBackground(null);
        undoButton.setMinHeight(70);
        undoButton.setOnAction(event -> {
            //Undo Button Functionality
        });

        gridPane.add(undoButton, 1, 0);

        Label redoLabel = new Label("↻");
        redoLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        redoLabel.setMinHeight(70);

        Button redoButton = new Button("", redoLabel);
        redoButton.setBackground(null);
        redoButton.setMinHeight(70);
        redoButton.setOnAction(event -> {
            //Redo Button Functionality
        });

        gridPane.add(redoButton, 2, 0);

        Label importLabel = new Label("Import ↓");
        importLabel.setFont(Font.font(20));

        Button importButton = new Button("", importLabel);
        importButton.setBackground(null);
        importButton.setMinHeight(70);
        importButton.setOnAction(event -> {
            //Import Personal Collection File
        });

        gridPane.add(importLabel, 3, 0);

        Label exportLabel = new Label("Export ↑");
        exportLabel.setFont(Font.font(20));

        Button exportButton = new Button("", exportLabel);
        exportButton.setBackground(null);
        exportButton.setMinHeight(70);
        exportButton.setOnAction(event -> {
            //Exports Personal Collection File
        });

        gridPane.add(exportButton, 4, 0);

        Label databaseLabel = new Label("Database");
        databaseLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

        Button databaseButton = new Button("", databaseLabel);
        databaseButton.setBackground(null);
        databaseButton.setOnAction(event -> {
            DatabasePage databasePage = new DatabasePage(proxyAccount);
            try {
                databasePage.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.getChildren().removeAll(nodes);
        });

        databaseButton.setMinHeight(70);
        gridPane.add(databaseButton, 5, 0);

        Label pcLabel = new Label("Personal Collection");
        pcLabel.setFont(Font.font(20));
        pcLabel.setMinHeight(70);
        pcLabel.setTextFill(Color.GRAY);
        gridPane.add(pcLabel, 6, 0);

        Label logoutLabel = new Label("Logout");
        logoutLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        logoutLabel.setMinHeight(70);
        logoutLabel.setTextFill(Color.BLUE);

        Button logoutButton = new Button("", logoutLabel);
        logoutButton.setBackground(null);
        logoutButton.setOnAction((event) -> {
            proxyAccount.logout();
            DatabasePage databasePage = new DatabasePage();
            try {
                databasePage.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.getChildren().removeAll(nodes);
        });

        gridPane.add(logoutButton, 7, 0);

        int spacing = proxyAccount.getUsername().length() > 6 ? (int)((35 + proxyAccount.getUsername().length()*5)/2.4) : (int)((45 + proxyAccount.getUsername().length()*3)/2.18);
        gridPane.setHgap(1000/spacing);
        gridPane.setMaxWidth(1000);
        gridPane.setPadding(new Insets(0, 0, 0, 5));

        group.getChildren().add(rect);
        group.getChildren().add(gridPane);
        root.getChildren().add(group);

        HBox hbox = new HBox();
        nodes.add(hbox);
        TextField field = new TextField();
        field.setPrefSize(350, 25);
        hbox.getChildren().add(field);

        ComboBox<String> searchStrategies = new ComboBox<>(FXCollections.observableArrayList(searchers));
        searchStrategies.setValue("Partial Search");
        hbox.getChildren().add(searchStrategies);

        ComboBox<String> searchMethod = new ComboBox<>(FXCollections.observableArrayList(searchOptions));
        searchMethod.setValue("Series Title");
        hbox.getChildren().add(searchMethod);

        ComboBox<String> sorter = new ComboBox<>(FXCollections.observableArrayList(sorters));
        sorter.setValue("Sort By Default");
        hbox.getChildren().add(sorter);

        Label buttonLabel = new Label("  ➔");
        // buttonLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        buttonLabel.setMinWidth(25);
        Button button = new Button("", buttonLabel);
        hbox.getChildren().add(button);
        button.setOnAction((event) -> {
                COMICS = proxyAccount.searchDatabase(searchStrategies.getValue(), sorter.getValue(), field.getText(), searchMethod.getValue());

        } );

        hbox.setSpacing(1000/17);
        hbox.setPadding(new Insets(0, 0, 0, 10));
        hbox.setMinWidth(1000);
        root.getChildren().add(hbox);

        VBox vBox = new VBox();

        nodes.add(vBox);

        Label comicListLabel = new Label("Comic List");
        comicListLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        comicListLabel.setPadding(new Insets(0, 0, 0, 5));
        vBox.getChildren().add(comicListLabel);

        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(50))));
        vBox.setPadding(new Insets(50));

        VBox comicListVBox = new VBox();
        nodes.add(comicListVBox);
        prevButtons = comicListUpdater(comicCounter);
        comicListVBox.getChildren().addAll(prevButtons);

        comicListVBox.setPadding(new Insets(0, 0, 0, 20));
        vBox.getChildren().add(comicListVBox);

        root.getChildren().add(vBox);

        HBox pages = new HBox();
        nodes.add(pages);
        Button leftButton = new Button("←");
        leftButton.setTextFill(Color.GRAY);
        leftButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pages.getChildren().add(leftButton);

        Label pageNumber = new Label();
        pageNumber.setText(COMICS.size() != 0 ? ("1/" + (int)Math.ceil(COMICS.size()/12)) : "0/0");
        pageNumber.setFont(new Font(18));
        pages.getChildren().add(pageNumber);

        Button rightButton = new Button("→");
        if (COMICS.size() != 0){
            rightButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            rightButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        rightButton.setOnAction(event -> {
            if (COMICS.size() != 0 && comicCounter < (int)Math.ceil(COMICS.size()/12)-1){
                leftButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                comicCounter++;
                pageNumber.setText((comicCounter) + "/" +  (int)Math.ceil(COMICS.size()/12));
                comicListVBox.getChildren().removeAll(prevButtons);
                prevButtons = comicListUpdater(comicCounter);
                comicListVBox.getChildren().addAll(prevButtons);
                if (proxyAccount.getComicCount() != 0 && comicCounter+1 > (int)Math.ceil(proxyAccount.getComicCount()/12)){
                    rightButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });
        
        leftButton.setOnAction(event -> {
            if (comicCounter > 1){
                rightButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                comicCounter--;
                comicListVBox.getChildren().removeAll(prevButtons);
                prevButtons = comicListUpdater(comicCounter);
                comicListVBox.getChildren().addAll(prevButtons);
                pageNumber.setText((comicCounter) + "/" +  (int)Math.ceil(proxyAccount.getComicCount()/12));
                if (comicCounter-1 <= 1){
                    leftButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });

        pages.getChildren().add(rightButton);

        pages.setSpacing(15);
        pages.setPadding(new Insets(0, 0, 0, 825));

        root.getChildren().add(pages);
        
        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Personal Collection");
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    private Collection<Button> comicListUpdater(int comicCount){
        List<Button> list = new ArrayList<>();
        if (COMICS.size() == 0){
            Label label = new Label("No Comics found.");
            label.setFont(new Font(15));
            Button button = new Button("", label);
            button.setBackground(null);
            list.add(button);
            return list;
        } 
        for (int i = (comicCounter-1)*10; i < 15 + ((comicCounter-1)*10); i++) {
            Label tempLabel;
            try {
                Marking comic = COMICS.get(i);
                tempLabel = new Label("• " + comic.getTitle() + ", Volume:" + comic.getVolumeNumber() + ", Issue #:" + comic.getIssueNumber());

            }
            catch (IndexOutOfBoundsException iobe){
                //Empty Label for spacing purposes
                tempLabel = new Label();
            }
            tempLabel.setFont(new Font(15));
            Button tempButton = new Button("", tempLabel);
            tempButton.setBackground(null);
            tempButton.setMaxHeight(10);
            tempButton.setOnAction(event -> {
                //Redirects to the comic's specific page when implemented
            });
            list.add(tempButton);
        }
        return list;
    }
    
}
