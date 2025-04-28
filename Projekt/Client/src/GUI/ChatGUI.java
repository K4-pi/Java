package GUI;

import javax.swing.*;
import java.awt.*;

public class ChatGUI {
    public void ShowChatGUI() {
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(750, 500);

        // Text display area
        JTextArea chatDisplay = new JTextArea();
        chatDisplay.setEditable(false);
        chatDisplay.setText("Hello World!");
        chatDisplay.setPreferredSize(new Dimension(500, 450));

        JPanel textDisplayPanel = new JPanel(new FlowLayout());
        textDisplayPanel.add(chatDisplay, new FlowLayout(FlowLayout.CENTER));

        // Text input fields
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 30));
        JTextField textInput = new JTextField();
        textInput.setPreferredSize(new Dimension(500, 30));

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

}
