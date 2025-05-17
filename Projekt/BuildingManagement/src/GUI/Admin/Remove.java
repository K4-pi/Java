package GUI.Admin;

import Database.UserDAO;
import GUI.CheckValue;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Remove extends Window {
    private final UserDAO userDAO = new UserDAO();
    private int errorCode = 0;
    private final JFrame parent;

    public Remove(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose, JFrame parent) {
        super(title, sizeX, sizeY, resizable, disposeOnClose);
        this.parent = parent;
    }

    public void run() {
        // Go back to admin panel
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    new AdminPanel("Admin panel", 800, 600, true).run();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        CardLayout mainLayout = new CardLayout();
        JPanel mainPanel = new JPanel(mainLayout);

        String[] panelNames = {"Remove user", "Remove apartment"};
        JComboBox<String> comboBox = new JComboBox<>(panelNames);
        comboBox.addActionListener(_ -> {
            String selectedPanel = (String) comboBox.getSelectedItem();
            mainLayout.show(mainPanel, selectedPanel);
        });
        mainPanel.add(removeUser(), "Remove user");
        mainPanel.add(removeApartment(), "Remove apartment");

        this.setLayout(new BorderLayout());
        this.add(comboBox, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel removeUser() {
        JPanel removeUserPanel = new JPanel();
        removeUserPanel.setLayout(new BoxLayout(removeUserPanel, BoxLayout.Y_AXIS));

        JLabel username = new JLabel("Username: ");
        JTextField usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(200, 30));
        JPanel combinedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton applyBtn = removeUserBtn(usernameField);

        combinedPanel.add(username);
        combinedPanel.add(usernameField);
        combinedPanel.add(applyBtn);

        removeUserPanel.add(combinedPanel);
        return removeUserPanel;
    }

    private JButton removeUserBtn(JTextField usernameField) {
        JButton removeBtn = new JButton("REMOVE");
        removeBtn.addActionListener(_ -> {
            String username = usernameField.getText();

            errorCode = userDAO.deleteUserDB(username);
            if (errorCode == 0) messageWindow("User: " + username + " removed!");
            else errorWindow("Error: " + errorCode);
        });
        return removeBtn;
    }

    private JPanel removeApartment() {
        JPanel removeApartmentPanel = new JPanel();
        removeApartmentPanel.setLayout(new BoxLayout(removeApartmentPanel, BoxLayout.Y_AXIS));

        JLabel apartment = new JLabel("Apartment NR: ");
        JTextField apartmentField = new JTextField(10);
        apartmentField.setPreferredSize(new Dimension(200, 30));
        JPanel combinedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton applyBtn = removeApartmentBtn(apartmentField);

        combinedPanel.add(apartment);
        combinedPanel.add(apartmentField);
        combinedPanel.add(applyBtn);

        removeApartmentPanel.add(combinedPanel);
        return removeApartmentPanel;
    }

    private JButton removeApartmentBtn(JTextField apartmentField) {
        JButton removeBtn = new JButton("REMOVE");
        removeBtn.addActionListener(_ -> {
            String apartment = apartmentField.getText();
            if (!CheckValue.isNumber(apartment)) {
                errorWindow("Apartment number must be a number!");
                return;
            }
            errorCode = userDAO.deleteApartmentDB(Integer.parseInt(apartment));
            if (errorCode == 0) messageWindow("Apartment nr: " + apartment + " removed!");
            else errorWindow("Error: " + errorCode);
        });
        return removeBtn;
    }
}