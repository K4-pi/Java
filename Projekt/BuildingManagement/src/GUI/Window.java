package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Window {

    // Create new window
    public JFrame setWindow(String title, int sizeX, int sizeY, boolean resizable) {
        JFrame frame = new JFrame(title);
        frame.setResizable(resizable);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setVisible(true);

        return frame;
    }

    // Panel that displays user info
    public JPanel updateUserInfoPanel(String user) throws SQLException {
        UserDAO userDAO = new UserDAO();
        String apartmentInfo = userDAO.apartmentInfo(userDAO.userId(user));

        JLabel userInfoLabel = new JLabel("<html>Hi " + user + "!<br/>" + apartmentInfo +"</html>", SwingConstants.LEFT);
        userInfoLabel.setVisible(true);
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new FlowLayout());
        userInfoPanel.add(userInfoLabel);

        return userInfoPanel;
    }

    // button that takes you back to main menu
    public JButton logOutButton() {
        JButton logoutBtn = new JButton("Log out");
        logoutBtn.setVisible(true);

        logoutBtn.addActionListener(_ -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
            new BuildingEntrance().run();
            frame.dispose();
        });

        return logoutBtn;
    }

}
