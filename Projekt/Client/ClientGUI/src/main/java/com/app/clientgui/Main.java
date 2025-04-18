package com.app.clientgui;

import com.app.clientgui.connection.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private final Client client = new Client();

    @Override
    public void start(Stage stage) {
        TextField ipTextField = new TextField("127.0.0.1");
        TextField portTextField = new TextField("9090");

        Text connectionError = new Text();
        connectionError.setFont(new Font(12));
        connectionError.setText("");

        Button connectButton = new Button("Connect");

        // Sproboj polaczyc sie z serverem
        connectButton.setOnAction(e -> {
            try {
                String ip = ipTextField.getText();
                int port = Integer.parseInt(portTextField.getText());

                client.startConnection(ip, port);
//                messageArea.appendText("Connected\n");
            }
            catch (Exception exception) {
                connectionError.setText(exception.getMessage()); // wyrzuca error pod przyciskiem
            }
        });

        VBox serverInfoBox = new VBox(5, new Label("Server IP:"), ipTextField, new Label("Port:"), portTextField, connectButton, connectionError);
        serverInfoBox.setPadding(new javafx.geometry.Insets(10));

        stage.setTitle("Client GUI");
        stage.setScene(new Scene(serverInfoBox, 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

