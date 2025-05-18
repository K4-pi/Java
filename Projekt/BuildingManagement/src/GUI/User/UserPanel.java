package GUI.User;

import GUI.Buttons;
import GUI.Window;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends Window {
    public UserPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximized) {
        super(title, sizeX, sizeY, resizable, maximized);
    }

//    public UserPanel(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose) {
//        super(title, sizeX, sizeY, resizable, disposeOnClose);
//    }

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel
        /*JLabel userInfo = window.updateUserInfoPanel(loggedUser);
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout());
        userInfoPanel.add(userInfo, BorderLayout.LINE_START);*/

        // Buttons
        JPanel btnsPanel = new JPanel(new GridLayout(1, 1));
        btnsPanel.add(Buttons.logOutButton());

//        mainPanel.add(userInfoPanel);
        mainPanel.add(btnsPanel);

        this.add(mainPanel);
    }
}
