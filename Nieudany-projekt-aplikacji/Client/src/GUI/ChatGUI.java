package GUI;

import connection.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ChatGUI {
    String receiveMsg = "";

    public void ShowChatGUI(Client serverClient) throws IOException {

        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(750, 500);

        //Text display area
        JTextArea chatDisplay = new JTextArea();
        chatDisplay.setEditable(false);
        chatDisplay.setPreferredSize(new Dimension(500, 450));

        if (!receiveMsg.isEmpty()) chatDisplay.append(receiveMsg);
        System.out.println(receiveMsg);

        JPanel textDisplayPanel = new JPanel(new FlowLayout());
        textDisplayPanel.add(chatDisplay, new FlowLayout(FlowLayout.CENTER));

        // Text input fields
        JTextField textInput = new JTextField();
        textInput.setPreferredSize(new Dimension(500, 30));

        JButton sendButton = getJButton(serverClient, textInput, chatDisplay);

        JPanel textInputPanel = new JPanel(new FlowLayout());
        textInputPanel.add(textInput, new FlowLayout(FlowLayout.LEFT));
        textInputPanel.add(sendButton, new FlowLayout(FlowLayout.RIGHT));

        // main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textDisplayPanel, BorderLayout.CENTER);
        mainPanel.add(textInputPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel);
        frame.setVisible(true);

        Thread receiveBuffer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    receiveMsg = serverClient.receiveMessage();
                } catch (IOException e) {
                    System.out.println("Receive thread exiting: " + e.getMessage());
                    break;
                }
                chatDisplay.setText(receiveMsg);
            }
        });
        receiveBuffer.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Closing window...");
                System.out.println("Closing connection with server...");
                try {
                    serverClient.stopConnection(); //close connection with server
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                receiveBuffer.interrupt();
            }
        });
    }

    private JButton getJButton(Client serverClient, JTextField textInput, JTextArea chatDisplay) {
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 30));
        sendButton.addActionListener(e -> {
            try {
                serverClient.sendMessage(textInput.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            chatDisplay.setText(receiveMsg);
            textInput.setText(""); //clearing message box after sending it
        });
        return sendButton;
    }
}
