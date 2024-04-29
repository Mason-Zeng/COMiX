package view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Comic;
import model.Creator;
import model.accounts.ProxyAccount;
import model.hierarchy.Publisher;
import model.hierarchy.Series;
import model.hierarchy.Volume;
import model.marking.Marking;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ComicCreationPage extends Application{
    private final Collection<Node> nodes = new ArrayList<>();
    private ProxyAccount proxyAccount;
    private Marking comic;

    public ComicCreationPage(ProxyAccount proxyAccount){
        this.proxyAccount = proxyAccount;
        this.comic = new Comic("N/A", "N/A", "N/A", new BigDecimal(1), LocalDate.of(2000, 1, 1), new Volume("N/A", new Series("N/A", new Publisher("N/A"))));
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
        importLabel.setMinHeight(70);
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

        int spacing = proxyAccount.getUsername().length() > 6 ? (int)((35 + proxyAccount.getUsername().length()*5)/2.05) : (int)((45 + proxyAccount.getUsername().length()*3)/1.93);
        gridPane.setHgap(999/spacing);
        gridPane.setMaxWidth(1000);
        gridPane.setPadding(new Insets(0, 0, 0, 5));

        group.getChildren().add(rect);
        group.getChildren().add(gridPane);
        root.getChildren().add(group);

        VBox vbox = new VBox();

        HBox seriesHBox = new HBox();
        Label seriesTitle = new Label("Series: ");
        seriesTitle.setFont(Font.font(20));

        TextField seriesTextField = new TextField();
        seriesTextField.setPrefSize(300, 30);

        seriesHBox.getChildren().add(seriesTitle);
        seriesHBox.getChildren().add(seriesTextField);
        vbox.getChildren().add(seriesHBox);

        HBox issueBox = new HBox();
        Label issueTitle = new Label("Issue Title: ");
        issueTitle.setFont(new Font(20));

        TextField issueField = new TextField();
        issueField.setPrefSize(300, 30);

        issueBox.getChildren().add(issueTitle);
        issueBox.getChildren().add(issueField);
        vbox.getChildren().add(issueBox);

        HBox issueNumBox = new HBox();
        Label issueNumber = new Label("Issue #");
        issueNumber.setFont(new Font(20));

        TextField issueNumField = new TextField();
        issueNumField.setPrefSize(300, 30);

        issueNumBox.getChildren().add(issueNumber);
        issueNumBox.getChildren().add(issueNumField);
        vbox.getChildren().add(issueNumBox);

        HBox volNumBox = new HBox();
        Label volumeNumber = new Label("Volume #");
        volumeNumber.setFont(new Font(20));

        TextField volNumField = new TextField();
        volNumField.setPrefSize(300, 30);

        volNumBox.getChildren().add(volumeNumber);
        volNumBox.getChildren().add(volNumField);
        vbox.getChildren().add(volNumBox);

        HBox creatorBox = new HBox();
        Label creator = new Label("Creator(s): ");
        creator.setFont(new Font(20));

        TextField creatorField = new TextField();
        creatorField.setPrefSize(300, 30);

        creatorBox.getChildren().add(creator);
        creatorBox.getChildren().add(creatorField);
        vbox.getChildren().add(creatorBox);

        HBox pubBox = new HBox();
        Label pubName = new Label("Publisher Name: ");
        pubName.setFont(new Font(20));

        TextField pubField = new TextField();
        pubField.setPrefSize(300, 30);

        pubBox.getChildren().add(pubName);
        pubBox.getChildren().add(pubField);
        vbox.getChildren().add(pubBox);

        HBox pubDateBox = new HBox();
        Label pubDate = new Label("Publication Date (YYYY-MM-DD): ");
        pubDate.setFont(new Font(20));

        TextField pubDateField = new TextField();
        pubDateField.setPrefSize(300, 30);

        pubDateBox.getChildren().add(pubDate);
        pubDateBox.getChildren().add(pubDateField);
        vbox.getChildren().add(pubDateBox);

        HBox valueBox = new HBox();
        Label value = new Label("Value: $");
        value.setFont(new Font(20));

        TextField valueField = new TextField();
        valueField.setPrefSize(300, 30);

        valueBox.getChildren().add(value);
        valueBox.getChildren().add(valueField);
        vbox.getChildren().add(valueBox);
        
        VBox vbox2 = new VBox();

        Label buttonLabel = new Label("Add to\nPersonal Collection");
        buttonLabel.setFont(new Font(25));
        buttonLabel.setTextFill(Color.WHITE);
        buttonLabel.setTextAlignment(TextAlignment.CENTER);

        Button button = new Button("", buttonLabel);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(70, 97, 161), 
            CornerRadii.EMPTY, Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, 
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        button.setOnAction(event -> {
            if (!(issueField.getText().isEmpty() || issueNumField.getText().isEmpty() ||volNumField.getText().isEmpty() ||
            seriesTextField.getText().isEmpty() || pubField.getText().isEmpty() || creatorField.getText().isEmpty() ||
            pubDateField.getText().isEmpty() || valueField.getText().isEmpty())){
                this.comic.setTitle(issueField.getText());
                this.comic.setIssueNumber(issueNumField.getText());
                this.comic.setVolume(new Volume(volNumField.getText(), new Series(seriesTextField.getText(), new Publisher(pubField.getText()))));

                String[] tokens = creatorField.getText().split(",");
                for (int i = 0; i < tokens.length; i++) {
                    this.comic.addCreator(new Creator(tokens[i]));
                }

                if (pubDateField.getText().length() == 10){
                    try {
                        this.comic.setDate(LocalDate.parse(pubDateField.getText()));
                    }
                    catch (DateTimeParseException e){}
                }

                try {
                    this.comic.setValue(new BigDecimal(Double.valueOf(valueField.getText())).setScale(2, RoundingMode.HALF_EVEN));
                }
                catch (NumberFormatException e){}

                proxyAccount.addComicToCollection(this.comic);
            }

            else {
                Label invalidLabel = new Label("All blanks need to be filled out!");
                invalidLabel.setTextFill(Color.RED);
                invalidLabel.setFont(new Font(20));
                if (vbox2.getChildren().size() < 2){
                    vbox2.getChildren().add(invalidLabel);
                }
                return;
            }
            DatabasePage databasePage = new DatabasePage(proxyAccount);
            try {
                databasePage.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.getChildren().removeAll(nodes);
        });

        vbox2.getChildren().add(button);
        vbox2.setAlignment(Pos.CENTER);

        vbox.getChildren().add(vbox2);

        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(25, 25, -25, 25))));
        vbox.setPadding(new Insets(50, 50, 0, 50));
        vbox.setSpacing(10);
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Comic Creator");
        primaryStage.show();
    }
    
}
