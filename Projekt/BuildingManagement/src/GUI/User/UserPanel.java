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
        JPanel combine = new JPanel(new BorderLayout());

        // User info panel
        JTextArea infoArea = new JTextArea(10, 13);
        JScrollPane scrollPane = new JScrollPane(infoArea);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(scrollPane);

        // logout button
        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.add(CustomComponents.logOutButton());

        combine.add(infoPanel, BorderLayout.CENTER);
        combine.add(logoutPanel, BorderLayout.SOUTH);

        mainPanel.add(combine);

        this.add(mainPanel);
    }


}
