package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserPanel {
    private final static Window window = new Window();

    public static void run(String loggedUser) throws SQLException {
        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel
        JPanel userInfoPanel = window.updateUserInfoPanel(loggedUser);

        // Buttons
        JPanel btnsPanel = new JPanel();
        btnsPanel.setLayout(new GridLayout(1, 1));
        btnsPanel.add(window.logOutButton());

        mainPanel.add(userInfoPanel);
        mainPanel.add(btnsPanel);

        mainFrame.add(mainPanel);
    }
}
