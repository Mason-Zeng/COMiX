package view;

import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.accounts.ProxyAccount;
import javafx.scene.Group;
import javafx.scene.Node;

public class LoginPage extends Application{
    private final Collection<Node> nodes = new ArrayList<>();
    private ProxyAccount account;

    public LoginPage(ProxyAccount account){
        this.account = account;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        nodes.add(root);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(25);

        Label loginLabel = new Label("Login Page");
        loginLabel.setFont(Font.font(null, FontWeight.BOLD, 40));
        loginLabel.setPadding(new Insets(0, 0, 20, 0));
        root.getChildren().add(loginLabel);

        Group group = new Group();

        TextField field = new TextField();
        field.setPadding(new Insets(20));
        field.setAlignment(Pos.CENTER);
        field.setFont(new Font(20));
        group.getChildren().add(field);
        root.getChildren().add(group);

        Label submitLabel = new Label("Submit");
        submitLabel.setFont(new Font(25));

        Button submitButton = new Button("", submitLabel);
        submitButton.setOnAction(event -> {
            if (!field.getText().isEmpty() && !field.getText().toLowerCase().equals("guest")){
                account.login(field.getText());
                DatabasePage databasePage = new DatabasePage(account);
                try {
                    databasePage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
            }
        });
        root.getChildren().add(submitButton);

        Label goBackLabel = new Label("← Go back");
        goBackLabel.setFont(new Font(25));
        goBackLabel.setTextFill(Color.BLUE);

        Button goBack = new Button("", goBackLabel);
        goBack.setOnAction(event -> {
            DatabasePage databasePage = new DatabasePage();
                try {
                    databasePage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
        });
        goBack.setBackground(null);
        root.getChildren().add(goBack);


        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Login");
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    
}
