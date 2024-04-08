package unitXX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        Rectangle rect = new Rectangle(1000, 70, Color.LIGHTGRAY);
        root.getChildren().add(rect);

        Label test = new Label("No results.");

        HBox hbox = new HBox();
        TextField field = new TextField();
        hbox.getChildren().add(field);

        Button button = new Button("Click me!");
        hbox.getChildren().add(button);

        ComboBox<String> searchStrategies = new ComboBox<>(FXCollections.observableArrayList(searchers));
        searchStrategies.setValue("Partial Search");
        hbox.getChildren().add(searchStrategies);

        ComboBox<String> searchMethod = new ComboBox<>(FXCollections.observableArrayList(searchOptions));
        searchMethod.setValue("Series Title");
        hbox.getChildren().add(searchMethod);

        ComboBox<String> sorter = new ComboBox<>(FXCollections.observableArrayList(sorters));
        sorter.setValue("Sort By Default");
        hbox.getChildren().add(sorter);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                proxyAccount.searchDatabase(searchStrategies.getValue(), sorter.getValue(), field.getText(), searchMethod.getValue());
            }

        } );

        hbox.setSpacing(5);
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
