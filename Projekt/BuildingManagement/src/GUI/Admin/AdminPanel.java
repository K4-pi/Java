package GUI.Admin;

import Database.UserDAO;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanel extends UserDAO {
    private final GUI.Window window = new Window();

    public void run(String loggedUser) throws SQLException {
        JFrame mainFrame = window.setWindow(loggedUser + " panel", 800, 600, false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Buttons
        JButton closeBtn = closeBuildingButton();
        JPanel btnsPanel = new JPanel();
        btnsPanel.setLayout(new GridLayout(1, 2));
        btnsPanel.add(closeBtn);
        btnsPanel.add(window.logOutButton());

        // Apartments list
        JScrollPane apartmentsList = apartmentsList();
        JPanel apartmentsListPanel = new JPanel();
        apartmentsListPanel.setLayout(new FlowLayout());
        apartmentsListPanel.add(apartmentsList);

        /*JComboBox<Integer> apartmentsListBox = showApartmentsList();
        JPanel apartmentsListPanel = new JPanel();
        apartmentsListPanel.setLayout(new FlowLayout());
        apartmentsListPanel.add(apartmentsListBox);*/

        // main panel
//        mainPanel.add(apartmentsListPanel);
        mainPanel.add(apartmentsListPanel, BorderLayout.CENTER);
        mainPanel.add(btnsPanel, BorderLayout.PAGE_END);

        mainFrame.add(mainPanel);
    }

    /*private JComboBox<Integer> showApartmentsList() throws SQLException {
        JComboBox<Integer> apartmnetsListBox;
        ArrayList<Integer> apartamentNumbers = apartmentsList();
        Integer[] list = new Integer[apartamentNumbers.size()];

        for (int i = 0; i < apartamentNumbers.size(); i ++) {
            list[i] = apartamentNumbers.get(i);
        }

        apartmnetsListBox = new JComboBox<>(list);
        apartmnetsListBox.setVisible(true);
        return apartmnetsListBox;
    }*/

    // Show apartments list
    private JScrollPane apartmentsList() throws SQLException {
        JTextArea textArea = new JTextArea(25, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setVisible(true);
        scrollPane.setVisible(true);

        ArrayList<String> list = apartmentInfo();
        for (String s : list) {
            textArea.append(s + "\n");
        }

        return scrollPane;
    }

    // Closing building functionality
    private JButton closeBuildingButton() throws SQLException {
        JButton closeBtn = new JButton();
        if (isClosed()) closeBtn.setText("OPEN");
        else closeBtn.setText("CLOSE");

        closeBtn.addActionListener(_ -> {
            try {
                updateDoorStatus(!isClosed());
                if (isClosed()) closeBtn.setText("OPEN");
                else closeBtn.setText("CLOSE");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        closeBtn.setVisible(true);

        return closeBtn;
    }
}