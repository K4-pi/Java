module com.app.app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.app to javafx.fxml;
    exports com.app.app;
}