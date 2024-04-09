module view {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens view to javafx.fxml;
    exports view;
}
