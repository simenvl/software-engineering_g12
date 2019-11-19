module gruppe12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens gruppe12 to javafx.fxml;
    exports gruppe12;
}