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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        JLabel descriptionLabel = new JLabel("Describe the problem:");
        JLabel messageLabel = new JLabel("Message:");
        JTextArea messageArea = new JTextArea(10, 10);
        JTextField descriptionArea = new JTextField();
        descriptionArea.setMaximumSize(new Dimension(10000, 5));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);

        mainPanel.add(descriptionLabel);
        mainPanel.add(descriptionArea);
        mainPanel.add(messageLabel);
        mainPanel.add(messageScrollPane);
        mainPanel.add(sendButtonReport(messageArea, descriptionArea));
        this.add(mainPanel);
    }

    private JButton sendButtonReport(JTextArea messageArea, JTextField desccriptionArea) {
        JButton b = new JButton("Send");
        b.addActionListener(_ -> {
            int userId = userDAO.getUserId(username);
            String description = desccriptionArea.getText();
            String message = messageArea.getText();

            if (description.isEmpty() || message.isEmpty()) {
                errorWindow("Please fill in all fields");
                return;
            }

            errorCode = userDAO.addReport(message, description, username, apartmentNr, userId);
            if (errorCode == 0) messageWindow("Your report has been sent to the administrator");
            else errorWindow("Error code: " + errorCode);

            //Clears text on send
            messageArea.setText("");
            desccriptionArea.setText("");
        });
        return b;
    }
}