package GUI;

import javax.swing.*;

public class UserPanel {
    private final static Window window = new Window();

    public static void run(String loggedUser) {
        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel
        JPanel userInfoPanel = window.userInfoPanel(loggedUser);

        mainPanel.add(userInfoPanel);

        mainFrame.add(mainPanel);
    }
}
