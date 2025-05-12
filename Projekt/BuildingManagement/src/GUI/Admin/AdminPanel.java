package GUI.Admin;

import Database.UserDAO;
import GUI.Buttons;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPanel extends Window {
    private final UserDAO userDAO = new UserDAO();

    public AdminPanel(String title, int sizeX, int sizeY, boolean resizable) {
        super(title, sizeX, sizeY, resizable);
    }

    public void run() throws SQLException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Close button
        JPanel btnsPanel = new JPanel(new GridLayout(1, 2));
        btnsPanel.add(closeBuildingButton());

        // Logut button
        btnsPanel.add(Buttons.logOutButton());

        // List and reports panel
        JPanel combined = new JPanel(new GridLayout(1, 2));
        combined.add(apartmentListAndApartmentSelector());
        combined.add(apartmentListAndApartmentSelector());

        // main panel
        mainPanel.add(combined, BorderLayout.CENTER);
        mainPanel.add(btnsPanel, BorderLayout.PAGE_END);

        this.add(mainPanel);
    }

    // Show apartments list
    private JScrollPane apartmentsList() throws SQLException {
        JTextArea textArea = new JTextArea(25, 35);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setVisible(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        scrollPane.setVisible(true);

        ArrayList<String> list = userDAO.apartmentsInfo();
        for (String s : list) {
            textArea.append(s + "\n");
        }

        return scrollPane;
    }

    // List of apartments and field for choosing apartment
    private JPanel apartmentListAndApartmentSelector() throws SQLException {
        // Apartments list
        JScrollPane apartmentsList = apartmentsList();
        JPanel apartmentsListPanel = new JPanel(new FlowLayout());
        apartmentsListPanel.add(apartmentsList);

        // Select apartment field and button
        JTextArea chooseApartmentField = new JTextArea(1, 5);
        JButton applayBtn = new JButton("Apply");
        JPanel chooseApartmentPanel = new JPanel(new FlowLayout());
        chooseApartmentPanel.add(chooseApartmentField);
        chooseApartmentPanel.add(applayBtn);

        applayBtn.addActionListener(_ ->
        {
            this.setEnabled(false);

            // Get apartment number
            int nr = -1;
            String choosenNumber = chooseApartmentField.getText();
            if (!choosenNumber.isEmpty()) nr = Integer.parseInt(choosenNumber);

            try {
                if (nr > 0) new ApartmentPanel("Apartment panel", 400, 400, false, true, nr).run(this);
                else System.out.println("Invalid apartment number");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // List and field for choosing apartment
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
        combinedPanel.add(apartmentsListPanel);
        combinedPanel.add(chooseApartmentPanel);

        return combinedPanel;
    }

    // Closing building functionality
    private JButton closeBuildingButton() throws SQLException {
        JButton closeBtn = new JButton();
        if (userDAO.isClosed()) closeBtn.setText("OPEN BUILDING");
        else closeBtn.setText("CLOSE BUILDING");

        closeBtn.addActionListener(_ -> {
            try {
                userDAO.updateDoorStatus(!userDAO.isClosed());
                if (userDAO.isClosed()) closeBtn.setText("OPEN BUILDING");
                else closeBtn.setText("CLOSE BUILDING");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        closeBtn.setVisible(true);

        return closeBtn;
    }
}