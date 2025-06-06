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
    private JList<String> apartmentInfoArea;
    private JList<String> userInfoArea;
    String username;

    public UserPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximized, String username) {
        super(title, sizeX, sizeY, resizable, maximized);
        this.username = username;
    }

    public void run() throws SQLException {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        apartmentNr = userDAO.getUserApartment(username);

        // User/Apartment info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // User
        JLabel userLabel = new JLabel("USER");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInfoArea = new JList<>();
        userInfoArea.setFocusable(false);
        updateUserInfo();

        // Apartment
        JLabel apartmentLabel = new JLabel("APARTMENT");
        apartmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        apartmentInfoArea = new JList<>();
        apartmentInfoArea.setFocusable(false);
        updateApartmentInfo();

        infoPanel.add(userLabel);
        infoPanel.add(userInfoArea);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(apartmentLabel);
        infoPanel.add(apartmentInfoArea);

        // Buttons that update parameters
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonsPanel.add(CustomComponents.switchButtonDouble("Air temp", doubleUpListener("airtemp"), doubleDownListener("airtemp")));
        buttonsPanel.add(CustomComponents.switchButtonDouble("Water temp", doubleUpListener("watertemp"), doubleDownListener("watertemp")));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Electricity", booleanButtonOnListener("electricity"), booleanButtonOffListener("electricity")));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Light", booleanButtonOnListener("light"), booleanButtonOffListener("light")));

        // Logout button
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.add(sendReportPanel());
        logoutPanel.add(CustomComponents.logOutButton());

        JPanel combined = new JPanel(new GridLayout(1, 2));
        combined.add(infoPanel);
        combined.add(buttonsPanel);

        mainPanel.add(combined, BorderLayout.CENTER);
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

    private void updateUserInfo() throws SQLException {
        DefaultListModel<String> userModel = new DefaultListModel<>();
        ArrayList<String> userList = userDAO.getUser(username);
        userModel.addElement("ID: " + userList.get(0));
        userModel.addElement("Role: " + userList.get(1));
        userModel.addElement("Username: " + userList.get(2));
        userModel.addElement("PIN: " + userList.get(3));
        userInfoArea.setModel(userModel);
    }

    private void updateApartmentInfo() throws SQLException {
        DefaultListModel<String> apartmentModel = new DefaultListModel<>();
        ArrayList<Object> list = userDAO.getApartment(apartmentNr);
        apartmentModel.addElement("Apartment number: " + list.get(0));
        apartmentModel.addElement("Electricity: " + list.get(1));
        apartmentModel.addElement("Light: " + list.get(2));
        apartmentModel.addElement("Air temp: " + list.get(3) + " Celsius");
        apartmentModel.addElement("Water temp: " + list.get(4) + " Celsius");
        apartmentInfoArea.setModel(apartmentModel);
    }

    private ActionListener booleanButtonOnListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, true, apartmentNr);
                updateApartmentInfo();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener booleanButtonOffListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, false, apartmentNr);
                updateApartmentInfo();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener doubleUpListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, 0.1, apartmentNr);
                updateApartmentInfo();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener doubleDownListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, -0.1, apartmentNr);
                updateApartmentInfo();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
