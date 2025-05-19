package GUI.User;

import Database.UserDAO;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ReportPanel extends Window {
    UserDAO userDAO = new UserDAO();
    private int errorCode = 0;

    private final int apartmentNr;
    private final String username;

    public ReportPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximized, boolean disposeOnClose, String username, int apartmentNr) {
        super(title, sizeX, sizeY, resizable, maximized, disposeOnClose);
        this.username = username;
        this.apartmentNr = apartmentNr;
    }

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Go back to admin panel
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    new UserPanel("User panel", 800, 600, false, false, username).run();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JLabel messageLabel = new JLabel("Please describe the problem:");
        JTextArea messageArea = new JTextArea(10, 10);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        JButton sendButton = sendButtonReport(messageArea);

        mainPanel.add(new JPanel(new FlowLayout()).add(messageLabel));
        mainPanel.add(new JPanel(new FlowLayout()).add(scrollPane));
        mainPanel.add(new JPanel(new FlowLayout()).add(sendButton));
        this.add(mainPanel);
    }

    private JButton sendButtonReport(JTextArea messageArea) {
        JButton b = new JButton("Send");
        b.addActionListener(_ -> {
            int userId = userDAO.getUserId(username);
            String message = messageArea.getText();

            errorCode = userDAO.addReport(message, username, apartmentNr, userId);
            if (errorCode == 0) messageWindow("Your report has been sent to the administrator");
            else errorWindow("Error code: " + errorCode);

            messageArea.setText(""); //Clears text on send
        });
        return b;
    }
}