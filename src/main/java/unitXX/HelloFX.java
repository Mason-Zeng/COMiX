package unitXX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.accounts.ProxyAccount;
import model.marking.Marking;

public class HelloFX extends Application {
    private final ProxyAccount proxyAccount = new ProxyAccount();
    private final String searchers[] = {"Partial Search", "Exact Search"};
    private final String sorters[] = {"Sort By Default", "Sort By Date"};
    private final String searchOptions[] = {"Series Title", "Issue Number", "Story Title", "Publisher", "Creator", "Date"};
    private List<Marking> COMICS = proxyAccount.searchDatabase("Partial Search", "Sort By Default", "", "Series Title");;
    private int comicCounter = 1; 
    private Collection<Label> prevLabels;

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root = new VBox();
        HBox hbox = new HBox();
        Group group = new Group();

        Rectangle rect = new Rectangle(1000, 70, Color.LIGHTGRAY);

        Label username = new Label("Hello, " + proxyAccount.getUsername());
        username.setFont(Font.font(null, FontWeight.BOLD, 25));
        username.setMinHeight(70);
        hbox.getChildren().add(username);

        Label undo = new Label("↺");
        undo.setFont(Font.font(null, FontWeight.BOLD, 25));
        undo.setMinHeight(70);
        undo.setTextFill(Color.GRAY);

        hbox.getChildren().add(undo);

        Label redo = new Label("↻");
        redo.setFont(Font.font(null, FontWeight.BOLD, 25));
        redo.setMinHeight(70);
        redo.setTextFill(Color.GRAY);

        hbox.getChildren().add(redo);

        Label importLabel = new Label("Import ↓");
        importLabel.setFont(Font.font(20));
        importLabel.setMinHeight(70);
        importLabel.setTextFill(Color.GRAY);
        hbox.getChildren().add(importLabel);

        Label exportLabel = new Label("Export ↑");
        exportLabel.setFont(Font.font(20));
        exportLabel.setMinHeight(70);
        exportLabel.setTextFill(Color.GRAY);
        hbox.getChildren().add(exportLabel);

        Label database = new Label("Database");
        database.setFont(Font.font(20));
        database.setMinHeight(70);
        database.setTextFill(Color.GRAY);
        hbox.getChildren().add(database);

        Label pcLabel = new Label("Personal Collection");
        pcLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        pcLabel.setMinHeight(70);

        Button pcButton = new Button("", pcLabel);
        pcButton.setBackground(null);
        pcButton.setOnAction((event) -> {
            //Redirects to login page
        });

        hbox.getChildren().add(pcButton);

        Label loginLabel = new Label("Login");
        loginLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        loginLabel.setMinHeight(70);
        loginLabel.setTextFill(Color.BLUE);

        Button loginButton = new Button("", loginLabel);
        loginButton.setBackground(null);
        loginButton.setOnAction((event) -> {
            //Redirects to login page
        });

        hbox.getChildren().add(loginButton);

        hbox.setSpacing(1000/21);
        hbox.setPadding(new Insets(0, 0, 0, 5));

        group.getChildren().add(rect);
        group.getChildren().add(hbox);
        root.getChildren().add(group);

        hbox = new HBox();
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

        Label comicListLabel = new Label("Comic List");
        comicListLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        vBox.getChildren().add(comicListLabel);

        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(50))));
        vBox.setPadding(new Insets(50));

        VBox comicListVBox = new VBox();
        prevLabels = comicListUpdater(comicCounter);
        comicListVBox.getChildren().addAll(prevLabels);

        comicListVBox.setPadding(new Insets(0, 0, 0, 20));
        vBox.getChildren().add(comicListVBox);

        root.getChildren().add(vBox);

        HBox pages = new HBox();
        Button leftButton = new Button("←");
        leftButton.setTextFill(Color.GRAY);
        leftButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pages.getChildren().add(leftButton);

        //HardCoded to the amount of Comics in Database
        Label pageNumber = new Label("1/" + (int)Math.ceil(14303/15));
        pageNumber.setFont(new Font(18));
        pages.getChildren().add(pageNumber);

        Button rightButton = new Button("→");
        rightButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        rightButton.setOnAction(event -> {
            if (comicCounter < (int)Math.ceil(14303/15)-1){
                leftButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                comicCounter++;
                pageNumber.setText((comicCounter) + "/" +  (int)Math.ceil(14303/15));
                comicListVBox.getChildren().removeAll(prevLabels);
                prevLabels = comicListUpdater(comicCounter);
                comicListVBox.getChildren().addAll(prevLabels);
                if (comicCounter+1 >= (int)Math.ceil(14303/15)){
                    rightButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });
        
        leftButton.setOnAction(event -> {
            if (comicCounter > 1){
                rightButton.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                comicCounter--;
                comicListVBox.getChildren().removeAll(prevLabels);
                prevLabels = comicListUpdater(comicCounter);
                comicListVBox.getChildren().addAll(prevLabels);
                pageNumber.setText((comicCounter) + "/" +  (int)Math.ceil(14303/15));
                if (comicCounter-1 <= 1){
                    leftButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });

        pages.getChildren().add(rightButton);

        pages.setSpacing(15);
        pages.setPadding(new Insets(0, 0, 0, 850));

        root.getChildren().add(pages);
        
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Hello, World!");
        primaryStage.show();
    }

    private Collection<Label> comicListUpdater(int comicCount){
        List<Label> list = new ArrayList<>();
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
            list.add(tempLabel);
        }
        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
