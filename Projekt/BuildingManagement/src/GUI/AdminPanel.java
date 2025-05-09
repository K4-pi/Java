package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.awt.*;
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

        // Buttons
        JButton closeBtn = closeBuildingButton();
        JPanel btnsPanel = new JPanel();
        btnsPanel.setLayout(new GridLayout(1, 2));
        btnsPanel.add(closeBtn);

        btnsPanel.add(window.logOutButton());

        mainPanel.add(userInfoPanel);
        mainPanel.add(btnsPanel);

        mainFrame.add(mainPanel);
    }

    // Closing building functionality
    private JButton closeBuildingButton() {
        JButton closeBtn = new JButton("Close building");

        closeBtn.addActionListener(e -> {
            try {
                updateDoorStatus(!isClosed());
                if (isClosed()) closeBtn.setText("OPEN");
                else closeBtn.setText("CLOSE");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        return closeBtn;
    }
}