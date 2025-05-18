package GUI.User;

import Database.UserDAO;
import GUI.CustomComponents;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPanel extends Window {
    private final UserDAO userDAO = new UserDAO();
    private int apartmentNr;
    private JTextArea infoArea;
    String username;

    public UserPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximized, String username) {
        super(title, sizeX, sizeY, resizable, maximized);
        this.username = username;
    }

    public void run() throws SQLException {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        apartmentNr = userDAO.getUserApartment(username);

        // User info panel
        infoArea = new JTextArea(12, 50);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoArea.setEditable(false);
        infoArea.setFocusable(false);
        updateInfo(infoArea);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(scrollPane, BorderLayout.WEST);

        // Buttons that update parameters
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonsPanel.add(CustomComponents.switchButtonDouble("Air temp", doubleUpListener("airtemp"), doubleDownListener("airtemp")));
        buttonsPanel.add(CustomComponents.switchButtonDouble("Water temp", doubleUpListener("watertemp"), doubleDownListener("watertemp")));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Electricity", booleanButtonOnListener("electricity"), booleanButtonOffListener("electricity")));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Light", booleanButtonOnListener("light"), booleanButtonOffListener("light")));
        infoPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Logout button
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.add(sendReportPanel());
        logoutPanel.add(CustomComponents.logOutButton());

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    private JButton sendReportPanel() {
        JButton b = new JButton("File report");
        b.addActionListener(_ -> {
            this.dispose();
            new ReportPanel("Report panel", 400, 500, false, false, true, username, apartmentNr).run();
        });
        return b;
    }

    private void updateInfo(JTextArea infoText) throws SQLException {
        infoText.setText(" ".repeat(10) + "|User|" + "\n"); // Clear the text

        // User
        ArrayList<String> userList = userDAO.getUser(username);
        infoText.append("ID: " + userList.get(0) + "\n");
        infoText.append("Role: " + userList.get(1) + "\n");
        infoText.append("Username: " + userList.get(2) + "\n");
        infoText.append("PIN: " + userList.get(3) + "\n\n");

        infoText.append(" ".repeat(10) + "|Apartment|" + "\n");

        // Apartment
        ArrayList<Object> list = userDAO.getApartment(apartmentNr);
        infoText.append("Apartment number: " + list.get(0) + "\n");
        infoText.append("Electricity: " + list.get(1) + "\n");
        infoText.append("Light: " + list.get(2) + "\n");
        infoText.append("Air temp: " + list.get(3) + " Celsius\n");
        infoText.append("Water temp: " + list.get(4) + " Celsius\n");
    }

    private ActionListener booleanButtonOnListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, true, apartmentNr);
                updateInfo(infoArea);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener booleanButtonOffListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, false, apartmentNr);
                updateInfo(infoArea);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener doubleUpListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, 0.1, apartmentNr);
                updateInfo(infoArea);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener doubleDownListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, -0.1, apartmentNr);
                updateInfo(infoArea);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
