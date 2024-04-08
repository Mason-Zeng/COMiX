package unitXX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.accounts.ProxyAccount;

public class HelloFX extends Application {
    private final ProxyAccount proxyAccount = new ProxyAccount();
    private final String searchers[] = {"Partial Search", "Exact Search"};
    private final String sorters[] = {"Sort By Default", "Sort By Date"};
    private final String searchOptions[] = {"Series Title", "Issue Number", "Story Title", "Publisher", "Creator", "Date"};

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

        group.getChildren().add(rect);
        group.getChildren().add(hbox);
        root.getChildren().add(group);

        Label test = new Label("No results.");

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

        Label buttonLabel = new Label("➔");
        buttonLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        Button button = new Button("", buttonLabel);
        hbox.getChildren().add(button);
        button.setOnAction((event) -> {
                proxyAccount.searchDatabase(searchStrategies.getValue(), sorter.getValue(), field.getText(), searchMethod.getValue());

        } );

        hbox.setSpacing(1000/17);
        hbox.setMinWidth(1000);
        root.getChildren().add(hbox);
        root.getChildren().add(test);
        
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Hello, World!");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
