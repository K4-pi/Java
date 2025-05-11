package GUI.User;

import GUI.Entry;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserPanel {
    private final GUI.Window window = new Window(Entry.getLoggedUser() + "'s panel", 800, 600, true);

    public void run() throws SQLException {
//        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel
        /*JLabel userInfo = window.updateUserInfoPanel(loggedUser);
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add(userInfo, BorderLayout.LINE_START);*/

        // Buttons
        JPanel btnsPanel = new JPanel(new GridLayout(1, 1));
        btnsPanel.add(window.logOutButton());

//        mainPanel.add(userInfoPanel);
        mainPanel.add(btnsPanel);

        window.add(mainPanel);
    }
}
