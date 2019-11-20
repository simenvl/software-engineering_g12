module gruppe {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens gruppe to javafx.fxml;
    exports gruppe;
}