package GUI.User;

import GUI.Entry;
import GUI.LogOutButton;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserPanel extends Window {
    public UserPanel(String title, int sizeX, int sizeY, boolean resizable) {
        super(title, sizeX, sizeY, resizable);
    }

    public UserPanel(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose) {
        super(title, sizeX, sizeY, resizable, disposeOnClose);
    }
//    private final GUI.Window window = new Window(Entry.getLoggedUser() + "'s panel", 800, 600, true);

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
        btnsPanel.add(LogOutButton.logOutButton());

//        mainPanel.add(userInfoPanel);
        mainPanel.add(btnsPanel);

        this.add(mainPanel);
    }
}
