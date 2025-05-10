package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class AdminPanel extends UserDAO {
    private final Window window = new Window();

    public void run(String loggedUser) throws SQLException {
        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Buttons
        JButton closeBtn = closeBuildingButton();
        JPanel btnsPanel = new JPanel();
        btnsPanel.setLayout(new GridLayout(1, 2));
        btnsPanel.add(closeBtn);
        btnsPanel.add(window.logOutButton());

        // Apartments list
        JComboBox apartmentsListBox = showApartmentsList();
        JPanel apartmentsListPanel = new JPanel();
        apartmentsListPanel.setLayout(new FlowLayout());
        apartmentsListPanel.add(apartmentsListBox);

        // main panel
        mainPanel.add(apartmentsListPanel);
        mainPanel.add(btnsPanel);

        mainFrame.add(mainPanel);
    }

    private JComboBox<Integer> showApartmentsList() throws SQLException {
        JComboBox<Integer> apartmnetsListBox;
        ArrayList<Integer> apartamentNumbers = apartmentsList();
        Integer[] list = new Integer[apartamentNumbers.size()];

        for (int i = 0; i < apartamentNumbers.size(); i ++) {
            list[i] = apartamentNumbers.get(i);
        }

        apartmnetsListBox = new JComboBox<>(list);
        apartmnetsListBox.setVisible(true);
        return apartmnetsListBox;
    }

    // Closing building functionality
    private JButton closeBuildingButton() throws SQLException {
        JButton closeBtn = new JButton();
        if (isClosed()) closeBtn.setText("OPEN");
        else closeBtn.setText("CLOSE");

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