package GUI.User;

import GUI.CustomComponents;
import GUI.Window;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends Window {
    public UserPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximized) {
        super(title, sizeX, sizeY, resizable, maximized);
    }

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel


        // logout button
        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.add(CustomComponents.logOutButton());

        mainPanel.add(logoutPanel);

        this.add(mainPanel);
    }


}
