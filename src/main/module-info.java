module unitXX {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires supercsv;

    opens unitXX to javafx.fxml;
    exports unitXX;
}
