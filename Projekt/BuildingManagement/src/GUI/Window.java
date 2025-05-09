package GUI;

import javax.swing.*;
import java.awt.*;

public class Window {

    public JFrame setWindow(String title, int sizeX, int sizeY, boolean resizable) {
        JFrame frame = new JFrame(title);
        frame.setResizable(resizable);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setVisible(true);

        return frame;
    }

    public JPanel userInfoPanel(String user) {
        JLabel userInfoLabel = new JLabel("You are a " + user);
        userInfoLabel.setVisible(true);
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new FlowLayout());
        userInfoPanel.add(userInfoLabel);

        return userInfoPanel;
    }

    public JButton logOutButton() {
        JButton logoutBtn = new JButton("Log out");
        logoutBtn.setVisible(true);

        logoutBtn.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
            new BuildingEntrance().run();
            frame.dispose();
        });

        return logoutBtn;
    }

}
