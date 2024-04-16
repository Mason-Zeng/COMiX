package view;

import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.accounts.ProxyAccount;
import model.marking.Marking;

public class ComicInfoPC extends Application{
    private final Collection<Node> nodes = new ArrayList<>();
    private ProxyAccount proxyAccount;
    private Marking comic;

    public ComicInfoPC(ProxyAccount proxyAccount, Marking comic){
        this.proxyAccount = proxyAccount;
        this.comic = comic;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        GridPane gridPane = new GridPane();
        Group group = new Group();

        nodes.add(gridPane);
        nodes.add(group);
        nodes.add(root);

        Rectangle rect = new Rectangle(1000, 70, Color.LIGHTGRAY);

        Label username = new Label("Hello, " + proxyAccount.getUsername());
        username.setFont(Font.font(null, FontWeight.BOLD, (proxyAccount.getUsername().length() > 14) ? 20 : 25));
        username.setMinHeight(70);
        gridPane.add(username, 0, 0);

        Label undo = new Label("↺");
        undo.setFont(Font.font(null, FontWeight.BOLD, 25));
        undo.setMinHeight(70);
        undo.setTextFill(Color.GRAY);

        gridPane.add(undo, 1, 0);

        Label redo = new Label("↻");
        redo.setFont(Font.font(null, FontWeight.BOLD, 25));
        redo.setMinHeight(70);
        redo.setTextFill(Color.GRAY);

        gridPane.add(redo, 2, 0);

        Label importLabel = new Label("Import ↓");
        importLabel.setFont(Font.font(20));
        importLabel.setMinHeight(70);
        importLabel.setTextFill(Color.GRAY);
        gridPane.add(importLabel, 3, 0);

        Label exportLabel = new Label("Export ↑");
        exportLabel.setFont(Font.font(20));
        exportLabel.setMinHeight(70);
        exportLabel.setTextFill(Color.GRAY);

        gridPane.add(exportLabel, 4, 0);

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
        pcLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        pcLabel.setMinHeight(70);

        Button pcButton = new Button("", pcLabel);
        pcButton.setBackground(null);
        pcButton.setOnAction((event) -> {
                PCPage pcPage = new PCPage(proxyAccount);
                try {
                    pcPage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
        });
        gridPane.add(pcButton, 6, 0);

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

        int spacing = proxyAccount.getUsername().length() > 6 ? (int)((35 + proxyAccount.getUsername().length()*5)/2.72) : (int)((45 + proxyAccount.getUsername().length()*3)/2.4);
        gridPane.setHgap(999/spacing);
        gridPane.setMaxWidth(1000);
        gridPane.setPadding(new Insets(0, 0, 0, 5));

        group.getChildren().add(rect);
        group.getChildren().add(gridPane);
        root.getChildren().add(group);

        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Comic Info");
        primaryStage.show();
    }
    
}
