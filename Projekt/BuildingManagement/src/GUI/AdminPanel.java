package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AdminPanel extends UserDAO {
    private final Window window = new Window();

    public void run(String loggedUser) throws SQLException {
        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // User info panel
        JPanel userInfoPanel = window.userInfoPanel(loggedUser);

        // Close button
        JButton closeBtn = closeBuildingButton();
        JPanel closeBtnPanel = new JPanel();
        closeBtnPanel.setLayout(new BoxLayout(closeBtnPanel, BoxLayout.Y_AXIS));
        closeBtnPanel.add(closeBtn);

        mainPanel.add(userInfoPanel);
        mainPanel.add(closeBtnPanel);

        mainFrame.add(mainPanel);
    }

    // Closing building functionality
    private JButton closeBuildingButton() {
        JButton closeBtn = new JButton("Close building");

        closeBtn.addActionListener(e -> {
            try {
                updateDoorStatus(!isClosed());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return closeBtn;
    }
}