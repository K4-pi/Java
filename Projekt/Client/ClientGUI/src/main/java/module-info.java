module com.app.clientgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.clientgui to javafx.fxml;
    exports com.app.clientgui;
    exports com.app.clientgui.scenes;
    opens com.app.clientgui.scenes to javafx.fxml;
    exports com.app.clientgui.connection;
    opens com.app.clientgui.connection to javafx.fxml;
}