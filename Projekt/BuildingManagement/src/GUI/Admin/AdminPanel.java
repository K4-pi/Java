package GUI.Admin;

import Database.UserDAO;
import GUI.Entry;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanel extends UserDAO {
    private final GUI.Window window = new Window(Entry.getLoggedUser() + "'s panel", 800, 600, false);

    public void run() throws SQLException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Close button
        JButton closeBtn = closeBuildingButton();
        JPanel btnsPanel = new JPanel(new GridLayout(1, 2));
        btnsPanel.add(closeBtn);

        // Logut button
        btnsPanel.add(window.logOutButton());

        // Apartments list
        JScrollPane apartmentsList = apartmentsList();
        JPanel apartmentsListPanel = new JPanel(new FlowLayout());
        apartmentsListPanel.add(apartmentsList);

        // Select apartment button and field
        JTextArea chooseApartmentField = new JTextArea(1, 5);
        JButton applayBtn = new JButton("Apply");
        JPanel chooseApartmentPanel = new JPanel(new FlowLayout());
        chooseApartmentPanel.add(chooseApartmentField);
        chooseApartmentPanel.add(applayBtn);

        // List and field for choosing apartment
        JPanel combinedListButtonPanel = new JPanel();
        combinedListButtonPanel.setLayout(new BoxLayout(combinedListButtonPanel, BoxLayout.Y_AXIS));
        combinedListButtonPanel.add(apartmentsListPanel);
        combinedListButtonPanel.add(chooseApartmentPanel);

        // main panel
        mainPanel.add(combinedListButtonPanel, BorderLayout.CENTER);
        mainPanel.add(btnsPanel, BorderLayout.PAGE_END);

        window.add(mainPanel);
    }

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