package GUI;

import connection.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ChatGUI {
    public void ShowChatGUI() throws IOException {
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(750, 500);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Closing window");
                try {
                    Client.stopConnection(); //close connection with server
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Text display area
        JTextArea chatDisplay = new JTextArea();
        chatDisplay.setEditable(false);
        chatDisplay.setText("Hello \nWorld!");
        chatDisplay.setPreferredSize(new Dimension(500, 450));

        JPanel textDisplayPanel = new JPanel(new FlowLayout());
        textDisplayPanel.add(chatDisplay, new FlowLayout(FlowLayout.CENTER));

        // Text input fields
        JTextField textInput = new JTextField();
        textInput.setPreferredSize(new Dimension(500, 30));

        JButton sendButton = getJButton(textInput);

        JPanel textInputPanel = new JPanel(new FlowLayout());
        textInputPanel.add(textInput, new FlowLayout(FlowLayout.LEFT));
        textInputPanel.add(sendButton, new FlowLayout(FlowLayout.RIGHT));

        // main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textDisplayPanel, BorderLayout.CENTER);
        mainPanel.add(textInputPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // When send button pressed send message written in input field
    private static JButton getJButton(JTextField textInput) {
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 30));
        sendButton.addActionListener(e -> {
            try {
                Client.sendMessage(textInput.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Send button clicked!");
        });
        return sendButton;
    }

}
