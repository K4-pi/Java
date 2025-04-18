package com.app.clientgui.scenes;

import com.app.clientgui.connection.Client;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Chat {

    public void ChatScene(Client client) {
        TextField textField = new TextField();

        Button sendButton = new Button("Send");
        Button disconnectButton = new Button("Disconnect");

        TextArea messageArea = new TextArea();
        messageArea.setEditable(false);
        messageArea.setWrapText(true);

        // Wyslij wiadomosc
        sendButton.setOnAction(e -> {
            try {
                String response = client.sendMessage(textField.getText());
                messageArea.appendText("Sent: " + response);
                textField.setText("");
            }
            catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        });

        // Rozlacz z serwerem
        disconnectButton.setOnAction(e -> {
            try {
                client.stopConnection();
                messageArea.appendText("Disconnected from server...\n");
            }
            catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

}
