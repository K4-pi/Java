package GUI.Admin;

import Database.UserDAO;
import GUI.CustomComponents;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Remove extends Window {
    private final UserDAO userDAO = new UserDAO();
    private int errorCode = 0;

    public Remove(String title, int sizeX, int sizeY, boolean resizable, boolean maximized, boolean disposeOnClose) {
        super(title, sizeX, sizeY, resizable, maximized, disposeOnClose);
    }

    public void run() {
        // Go back to admin panel
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    new AdminPanel("Admin panel", 800, 600, true, true).run();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        CardLayout mainLayout = new CardLayout();
        JPanel mainPanel = new JPanel(mainLayout);

        String[] panelNames = {"Remove user", "Remove apartment", "Remove report"};
        JComboBox<String> comboBox = new JComboBox<>(panelNames);
        comboBox.addActionListener(_ -> {
            String selectedPanel = (String) comboBox.getSelectedItem();
            mainLayout.show(mainPanel, selectedPanel);
        });
        mainPanel.add(removeUser(), "Remove user");
        mainPanel.add(removeApartment(), "Remove apartment");
        mainPanel.add(removeReport(), "Remove report");

        this.setLayout(new BorderLayout());
        this.add(comboBox, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel removeReport() {
        JPanel removeReportPanel = new JPanel();
        removeReportPanel.setLayout(new BoxLayout(removeReportPanel, BoxLayout.Y_AXIS));
        JLabel reportLabel = new JLabel("Report ID: ");

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel componentsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField idField = new JTextField();
        idField.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(removeReportBtn(idField), BorderLayout.PAGE_END);
        componentsPanel.add(reportLabel);
        componentsPanel.add(idField);

        removeReportPanel.add(componentsPanel);
        removeReportPanel.add(buttonPanel);
        return removeReportPanel;
    }

    private JButton removeReportBtn(JTextField idField) {
        JButton removeBtn = new JButton("REMOVE");
        removeBtn.addActionListener(_ -> {
            String id = idField.getText();
            if (id.isEmpty()) {
                errorWindow("Provide report ID!");
                return;
            }
            if (!CustomComponents.isNumber(id)) {
                errorWindow("ID must be a number!");
                return;
            }
            errorCode = userDAO.deleteReportDB(Integer.parseInt(id));
            if (errorCode == 0) messageWindow("Report with ID: " + id + " removed!");
            else errorWindow("Error: " + errorCode);
        });
        return removeBtn;
    }

    private JPanel removeUser() {
        JPanel removeUserPanel = new JPanel();
        removeUserPanel.setLayout(new BoxLayout(removeUserPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JLabel username = new JLabel("Username: ");
        JTextField usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(200, 30));
        JPanel combinedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttonPanel.add(removeUserBtn(usernameField), BorderLayout.PAGE_END);
        combinedPanel.add(username);
        combinedPanel.add(usernameField);

        removeUserPanel.add(combinedPanel);
        removeUserPanel.add(buttonPanel);
        return removeUserPanel;
    }

    private JButton removeUserBtn(JTextField usernameField) {
        JButton removeBtn = new JButton("REMOVE");
        removeBtn.addActionListener(_ -> {
            String username = usernameField.getText();
            if (username.isEmpty()) {
                errorWindow("Provide username!");
                return;
            }
            if (username.equals("admin")) {
                errorWindow("You can't remove account 'admin'\nit is permanent account!");
                return;
            }
            errorCode = userDAO.deleteUserDB(username);
            if (errorCode == 0) messageWindow("User: " + username + " removed!");
            else errorWindow("Error: " + errorCode);
        });
        return removeBtn;
    }

    private JPanel removeApartment() {
        JPanel removeApartmentPanel = new JPanel();
        removeApartmentPanel.setLayout(new BoxLayout(removeApartmentPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JLabel apartment = new JLabel("Apartment NR: ");
        JTextField apartmentField = new JTextField(10);
        apartmentField.setPreferredSize(new Dimension(200, 30));
        JPanel combinedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttonPanel.add(removeApartmentBtn(apartmentField), BorderLayout.PAGE_END);
        combinedPanel.add(apartment);
        combinedPanel.add(apartmentField);

        removeApartmentPanel.add(combinedPanel);
        removeApartmentPanel.add(buttonPanel);
        return removeApartmentPanel;
    }

    private JButton removeApartmentBtn(JTextField apartmentField) {
        JButton removeBtn = new JButton("REMOVE");
        removeBtn.addActionListener(_ -> {
            String apartment = apartmentField.getText();
            if (!CustomComponents.isNumber(apartment)) {
                errorWindow("Apartment number must be a number!");
                return;
            }
            if (apartment.isEmpty()) {
                errorWindow("Provide apartment number!");
                return;
            }
            errorCode = userDAO.deleteApartmentDB(Integer.parseInt(apartment));
            if (errorCode == 0) messageWindow("Apartment nr: " + apartment + " removed!");
            else errorWindow("Error: " + errorCode);
        });
        return removeBtn;
    }
}