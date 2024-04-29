package view;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Creator;
import model.accounts.ProxyAccount;
import model.marking.Authenticate;
import model.marking.Grade;
import model.marking.Marking;
import model.marking.MarkingHandler;
import model.marking.Sign;
import model.marking.Slab;

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

        Group trueRoot = new Group();

        Rectangle popupRect = new Rectangle(1000, 1000, Color.LIGHTGRAY);
        popupRect.setOpacity(0.65);

        VBox popup1 = new VBox();
        popup1.setAlignment(Pos.CENTER);
        popup1.setPrefSize(1000, 650);
        popup1.setPadding(new Insets(150));
        popup1.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, new Insets(130))));
        
        Label popupLabel = new Label("Please select a grade for this comic.\n(1 Being the Lowest, 10 Being the Highest)");
        popupLabel.setFont(Font.font(null, FontWeight.BOLD, 30));
        popupLabel.setTextAlignment(TextAlignment.CENTER);

        Integer[] gradeOptions = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ComboBox<Integer> grades = new ComboBox<>(FXCollections.observableArrayList(gradeOptions));
        grades.setValue(1);

        Label confirmLabel = new Label("Confirm");
        confirmLabel.setFont(new Font(25));
        Button confirmButton = new Button("", confirmLabel);
        confirmButton.setAlignment(Pos.CENTER);

        Label cancelLabel = new Label("Cancel");
        cancelLabel.setFont(new Font(25));
        cancelLabel.setTextFill(Color.BLUE);
        Button cancelButton = new Button("", cancelLabel);
        cancelButton.setBackground(null);
        cancelButton.setOnAction(event -> {
            trueRoot.getChildren().remove(popup1);
            trueRoot.getChildren().remove(popupRect);
        });

        popup1.getChildren().add(popupLabel);
        popup1.getChildren().add(grades);
        popup1.getChildren().add(confirmButton);
        popup1.getChildren().add(cancelButton);
        popup1.setSpacing(25);

        VBox popup2 = new VBox();
        popup2.setAlignment(Pos.CENTER);
        popup2.setPrefSize(1000, 650);
        popup2.setPadding(new Insets(100));
        popup2.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, new Insets(80))));

        Label popupLabel2 = new Label("Please Select An Attribute to Edit.");
        popupLabel2.setFont(Font.font(null, FontWeight.BOLD, 30));
        popupLabel2.setTextAlignment(TextAlignment.CENTER);

        String[] editOptionsArray = {"Issue Title", "Issue Number", "Creator", "Date Published", "Value"};
        ComboBox<String> editOptions = new ComboBox<>(FXCollections.observableArrayList(editOptionsArray));
        editOptions.setValue("Issue Title");

        TextField field = new TextField();
        field.setPrefSize(350, 25);
        field.setFont(new Font(15));

        editOptions.setOnAction(event -> {
            if (editOptions.getValue().equals("Creator")){
                popupLabel2.setText("Please Select An Attribute to Edit.\nFor Multiple Creators, Seperate by Commas.");
            }
            else if (editOptions.getValue().equals("Date Published")){
                popupLabel2.setText("Please Select An Attribute to Edit.\nFormat Should be YYYY-MM-DD");
            }
            else {
                popupLabel2.setText("Please Select An Attribute to Edit.");
            }
            field.setText("");
        });

        Label confirmLabel2 = new Label("Confirm");
        confirmLabel2.setFont(new Font(25));
        Button confirmButton2 = new Button("", confirmLabel2);
        confirmButton2.setAlignment(Pos.CENTER);

        Label cancelLabel2 = new Label("Cancel");
        cancelLabel2.setFont(new Font(25));
        cancelLabel2.setTextFill(Color.BLUE);
        Button cancelButton2 = new Button("", cancelLabel2);
        cancelButton2.setBackground(null);
        cancelButton2.setOnAction(event -> {
            trueRoot.getChildren().remove(popup2);
            trueRoot.getChildren().remove(popupRect);
        });

        popup2.getChildren().add(popupLabel2);
        popup2.getChildren().add(editOptions);
        popup2.getChildren().add(field);
        popup2.getChildren().add(confirmButton2);
        popup2.getChildren().add(cancelButton2);
        popup2.setSpacing(25);

        VBox root = new VBox();
        GridPane gridPane = new GridPane();
        Group group = new Group();

        trueRoot.getChildren().add(root);
        nodes.add(trueRoot);

        nodes.add(gridPane);
        nodes.add(group);
        nodes.add(root);

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
            proxyAccount.undo();
            if (proxyAccount.getUsername() == "Guest"){
                LoginPage loginPage = new LoginPage(proxyAccount);
                try {
                    loginPage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
            }
            else{
                PCPage pcPage = new PCPage(proxyAccount);
                try {
                    pcPage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
            }
        });

        gridPane.add(undoButton, 1, 0);

        Label redoLabel = new Label("↻");
        redoLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        redoLabel.setMinHeight(70);

        Button redoButton = new Button("", redoLabel);
        redoButton.setBackground(null);
        redoButton.setMinHeight(70);
        redoButton.setOnAction(event -> {
            proxyAccount.redo();
            if (proxyAccount.getUsername() == "Guest"){
                LoginPage loginPage = new LoginPage(proxyAccount);
                try {
                    loginPage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
            }
            else{
                PCPage pcPage = new PCPage(proxyAccount);
                try {
                    pcPage.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                root.getChildren().removeAll(nodes);
            }
        });

        gridPane.add(redoButton, 2, 0);

        Label importLabel = new Label("Import ↓");
        importLabel.setFont(Font.font(20));
        importLabel.setTextFill(Color.GRAY);

        Button importButton = new Button("", importLabel);
        importButton.setBackground(null);
        importButton.setMinHeight(70);

        gridPane.add(importButton, 3, 0);

        Label exportLabel = new Label("Export ↑");
        exportLabel.setFont(Font.font(20));
        importLabel.setTextFill(Color.GRAY);

        Button exportButton = new Button("", exportLabel);
        exportButton.setBackground(null);
        exportButton.setMinHeight(70);
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
            trueRoot.getChildren().removeAll(nodes);
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
                trueRoot.getChildren().removeAll(nodes);
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
            trueRoot.getChildren().removeAll(nodes);
        });

        gridPane.add(logoutButton, 7, 0);

        int spacing = proxyAccount.getUsername().length() > 6 ? (int)((35 + proxyAccount.getUsername().length()*5)/2.05) : (int)((45 + proxyAccount.getUsername().length()*3)/1.93);
        gridPane.setHgap(999/spacing);
        gridPane.setMaxWidth(1000);
        gridPane.setPadding(new Insets(0, 0, 0, 5));

        group.getChildren().add(rect);
        group.getChildren().add(gridPane);
        root.getChildren().add(group);

        VBox vbox = new VBox();

        Label seriesTitle = new Label("Series Title: " + comic.getSeriesTitle());
        seriesTitle.setFont(Font.font(20));
        vbox.getChildren().add(seriesTitle);

        Label issueTitle = new Label("Issue Title: " + comic.getTitle());
        issueTitle.setFont(new Font(20));
        vbox.getChildren().add(issueTitle);

        Label issueNumber = new Label("Issue #" + comic.getIssueNumber());
        issueNumber.setFont(new Font(20));
        vbox.getChildren().add(issueNumber);

        Label volumeNumber = new Label("Volume #" + comic.getVolumeNumber());
        volumeNumber.setFont(new Font(20));
        vbox.getChildren().add(volumeNumber);

        String creators = "";
        for (int i = 0; i < comic.getCreators().size(); i++) {
            creators += comic.getCreators().get(i).getName();
            if (i < comic.getCreators().size() - 1){
                creators += ", ";
            }
        }

        Label creator = new Label("Creator(s): " + creators);
        creator.setFont(new Font(20));
        vbox.getChildren().add(creator);

        Label pubName = new Label("Publisher Name: " + comic.getPublisherName());
        pubName.setFont(new Font(20));
        vbox.getChildren().add(pubName);

        Label pubDate = new Label("Publication Date: " + comic.getDate().toString());
        pubDate.setFont(new Font(20));
        vbox.getChildren().add(pubDate);

        Label value = new Label("Value: $" + comic.getValue());
        value.setFont(new Font(20));
        vbox.getChildren().add(value);

        Label timesSigned = new Label("Number Of Signatures: " + MarkingHandler.signCount(comic));
        timesSigned.setFont(new Font(20));
        timesSigned.setTextFill(Color.rgb(39, 97, 166));
        vbox.getChildren().add(timesSigned);

        int tempGrade = MarkingHandler.getGrade(comic);
        Label grade = new Label("Grade: " + (tempGrade == 0 ? "N/A" : tempGrade));
        grade.setFont(new Font(20));
        grade.setTextFill(Color.rgb(57, 149, 57));
        vbox.getChildren().add(grade);

        Label authenticated = new Label("Authenticated? " + (MarkingHandler.isAuthenticated(comic) == true ? "Yes" : "No"));
        authenticated.setFont(new Font(20));
        authenticated.setTextFill(Color.RED);
        vbox.getChildren().add(authenticated);

        Label slabbed = new Label("Slabbed? " + (MarkingHandler.isSlabbed(comic) == true ? "Yes" : "No"));
        slabbed.setFont(new Font(20));
        slabbed.setTextFill(Color.rgb(118, 34, 139));
        vbox.getChildren().add(slabbed);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 0, 0, 0));

        Label editLabel = new Label("Edit");
        editLabel.setFont(new Font(20));
        Button editButton = new Button("", editLabel);
        editButton.setOnAction(event -> {
            trueRoot.getChildren().add(popupRect);
            trueRoot.getChildren().add(popup2);
        });
        grid.add(editButton, 0, 0);

        Label signLabel = new Label("Sign");
        signLabel.setFont(new Font(20));
        signLabel.setTextFill(Color.rgb(39, 97, 166));
        Button signButton = new Button("", signLabel);
        signButton.setOnAction(event -> {
            try {
                proxyAccount.removeComicFromCollection(comic);
                comic = new Sign(comic);
                proxyAccount.addComicToCollection(comic);
                timesSigned.setText("Number Of Signatures: " + MarkingHandler.signCount(comic));
                value.setText("Value: $" + comic.getValue());
                proxyAccount.exportCollection(new File("data/users/" + proxyAccount.getUsername() + ".json"));
            }
            catch (IllegalArgumentException e){}
            
        });
        grid.add(signButton, 1, 0);

        Label gradeLabel = new Label("Grade");
        gradeLabel.setFont(new Font(20));
        gradeLabel.setTextFill(Color.rgb(57, 149, 57));
        Button gradeButton = new Button("", gradeLabel);
        gradeButton.setOnAction(event -> {
            if (!MarkingHandler.isGrade(comic)){
                trueRoot.getChildren().add(popupRect);
                trueRoot.getChildren().add(popup1);
            }
        });
        grid.add(gradeButton, 2, 0);

        Label authenticateLabel = new Label("Authenticate");
        authenticateLabel.setFont(new Font(20));
        authenticateLabel.setTextFill(Color.RED);
        Button authenticateButton = new Button("", authenticateLabel);
        authenticateButton.setOnAction(event -> {
            try {
                proxyAccount.removeComicFromCollection(comic);
                comic = new Authenticate(comic);
                proxyAccount.addComicToCollection(comic);
                authenticated.setText("Authenticated? " + (MarkingHandler.isAuthenticated(comic) == true ? "Yes" : "No"));
                value.setText("Value: $" + comic.getValue());
                proxyAccount.exportCollection(new File("data/users/" + proxyAccount.getUsername() + ".json"));
            }
            catch (IllegalArgumentException e){}
        });
        grid.add(authenticateButton, 3, 0);

        Label slabLabel = new Label("Slab");
        slabLabel.setFont(new Font(20));
        slabLabel.setTextFill(Color.rgb(118, 34, 139));
        Button slabButton = new Button("", slabLabel);
        slabButton.setOnAction(event -> {
            try {
                proxyAccount.removeComicFromCollection(comic);
                comic = new Slab(comic);
                proxyAccount.addComicToCollection(comic);
                slabbed.setText("Slabbed? " + (MarkingHandler.isSlabbed(comic) == true ? "Yes" : "No"));
                value.setText("Value: $" + comic.getValue());
                proxyAccount.exportCollection(new File("data/users/" + proxyAccount.getUsername() + ".json"));
            }
            catch (Exception e){}
        });
        grid.add(slabButton, 4, 0);

        Label removeLabel = new Label("Remove Comic");
        removeLabel.setFont(new Font(20));
        removeLabel.setTextFill(Color.WHITE);
        Button removeButton = new Button("", removeLabel);
        removeButton.setBackground(null);
        removeButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        removeButton.setBorder(new Border(new BorderStroke(Color.BLACK, 
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        removeButton.setOnAction(event -> {
            proxyAccount.removeComicFromCollection(this.comic);
            PCPage pcPage = new PCPage(proxyAccount);
            try {
                pcPage.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.getChildren().removeAll(nodes);
        });
        grid.add(removeButton, 5, 0);

        grid.setHgap(75);
        vbox.getChildren().add(grid);

        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(25, 25, -25, 25))));
        vbox.setPadding(new Insets(30, 50, 0, 50));
        vbox.setSpacing(5);
        root.getChildren().add(vbox);

        confirmButton.setOnAction(event -> {
            try {
                proxyAccount.removeComicFromCollection(comic);
                comic = new Grade(comic, grades.getValue());
                proxyAccount.addComicToCollection(comic);
                trueRoot.getChildren().remove(popup1);
                trueRoot.getChildren().remove(popupRect);
                grade.setText("Grade: " + MarkingHandler.getGrade(comic));
                value.setText("Value: $" + comic.getValue());
                proxyAccount.exportCollection(new File("data/users/" + proxyAccount.getUsername() + ".json"));
            }
            catch (IllegalArgumentException e){}
        });

        confirmButton2.setOnAction(event -> {
            if (!field.getText().equals("")){
                if (editOptions.getValue().equals("Issue Title")){
                    comic.setTitle(field.getText().strip());
                    issueTitle.setText("Issue Title: " + comic.getTitle());
                }
                else if (editOptions.getValue().equals("Issue Number")){
                    try {
                        Integer.parseInt(field.getText());
                        comic.setIssueNumber(field.getText().strip());
                        issueNumber.setText("Issue #" + comic.getIssueNumber());
                    }
                    catch (NumberFormatException e){}
                }
                else if (editOptions.getValue().equals("Creator")){
                    String[] creatorTokens = field.getText().split(",");
                    for (String creatorString : creatorTokens) {
                        comic.addCreator(new Creator(creatorString.strip()));
                    }
                    creator.setText("Creator(s): " + field.getText());
                }
                else if (editOptions.getValue().equals("Date Published")){
                    if (field.getText().length() == 10){
                        try {
                            comic.setDate(LocalDate.parse(field.getText()));
                            pubDate.setText("Publication Date: " + comic.getDate().toString());
                        }
                        catch (DateTimeParseException e){}
                    }
                }
                else {
                    try {
                        comic.setValue(new BigDecimal(Double.valueOf(field.getText())).setScale(2, RoundingMode.HALF_EVEN));
                        value.setText("Value: $" + comic.getValue());
                    }
                    catch (NumberFormatException e){}
                }
                trueRoot.getChildren().remove(popup2);
                trueRoot.getChildren().remove(popupRect);
                proxyAccount.exportCollection(new File("data/users/" + proxyAccount.getUsername() + ".json"));
            }
        });

        Scene scene = new Scene(trueRoot, 1000, 650);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Comic Info");
        primaryStage.show();
    }
    
}
