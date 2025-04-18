package com.app.clientgui;

import com.app.clientgui.connection.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final Client client = new Client();

    @Override
    public void start(Stage primaryStage) {
        TextField ipTextField = new TextField("127.0.0.1");
        TextField portTextField = new TextField("9090");
        TextField inputField = new TextField();

        Button connectButton = new Button("Connect");
        Button sendButton = new Button("Send");
        Button disconnectButton = new Button("Disconnect");

        TextArea messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setWrapText(true);

        connectButton.setOnAction(e -> {
            try {
                String ip = ipTextField.getText();
                int port = Integer.parseInt(portTextField.getText());

                client.startConnection(ip, port);
                messageArea.appendText("Connected\n");
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        sendButton.setOnAction(e -> {
            try {
                String response = client.sendMessage(inputField.getText());
                messageArea.appendText("Sent: " + response);
                inputField.setText("");
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        });

        disconnectButton.setOnAction(e -> {
            try {
                client.stopConnection();
                messageArea.appendText("Disconnected from server...\n");
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        VBox topBox = new VBox(5, new Label("Server IP:"), ipTextField, new Label("Port:"), portTextField, connectButton);
        HBox bottomBox = new HBox(5, inputField, sendButton, disconnectButton);
        VBox root = new VBox(10, topBox, messageArea, bottomBox);
        root.setPadding(new javafx.geometry.Insets(10));

        primaryStage.setTitle("Client GUI");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

