package GUI;

import Database.UserDAO;

import javax.swing.*;

public class AdminPanel extends UserDAO {
    private final Window window = new Window();

    public void run(String loggedUser) {
        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel
        JPanel userInfoPanel = window.userInfoPanel(loggedUser);

        // Closing building functionality
        JButton closeBuildingButton = new JButton("Close building");

        mainPanel.add(userInfoPanel);

        mainFrame.add(mainPanel);
    }


}