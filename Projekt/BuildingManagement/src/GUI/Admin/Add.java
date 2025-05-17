package GUI.Admin;

import Database.UserDAO;
import GUI.CheckValue;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Objects;

public class Add extends Window  {
    private final UserDAO userDAO = new UserDAO();

    private int errorCode = 0;

    public Add(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose) {
        super(title, sizeX, sizeY, resizable, disposeOnClose);
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

        String[] panelNames = {"Add user", "Add apartment", "Add user to apartment"};
        JComboBox<String> comboBox = new JComboBox<>(panelNames);
        comboBox.addActionListener(_ -> {
            String selectedPanel = (String) comboBox.getSelectedItem();
            mainLayout.show(mainPanel, selectedPanel);
        });
        mainPanel.add(addUser(), "Add user");
        mainPanel.add(addApartment(), "Add apartment");
        mainPanel.add(addUserToApartment(), "Add user to apartment");

        this.setLayout(new BorderLayout());
        this.add(comboBox, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel addUserToApartment() {
        JPanel addUserToApartmentPanel = new JPanel(new GridLayout(4, 1));

        JLabel addUserToApartmentLabel = new JLabel("  Add user to apartment: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel apartmentLabel = new JLabel("Apartment: ");

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel apartmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField username = new JTextField(10);
        username.setPreferredSize(new Dimension(100, 30));

        JTextField apartment = new JTextField(10);
        apartment.setPreferredSize(new Dimension(100, 30));

        JButton addBtn = addUserToApartmentBtn(username, apartment);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        apartmentPanel.add(apartmentLabel);
        apartmentPanel.add(apartment);

        addUserToApartmentPanel.add(addUserToApartmentLabel);
        addUserToApartmentPanel.add(usernamePanel);
        addUserToApartmentPanel.add(apartmentPanel);
        addUserToApartmentPanel.add(addBtn, new FlowLayout(FlowLayout.LEFT));

        return addUserToApartmentPanel;
    }

    private JButton addUserToApartmentBtn(JTextField username, JTextField apartment) {
        JButton addBtn = new JButton("ADD");

        addBtn.addActionListener(_ -> {
            String usernameValue = username.getText();
            String apartmentValue = apartment.getText();

            if (!CheckValue.isNumber(apartmentValue)) {
                errorWindow("Apartment number must be a number!");
                return;
            }
            if (usernameValue.length() > 25) {
                errorWindow("Usernames must be less than 25 characters!");
                return;
            }

            errorCode = userDAO.addUserToApartmentDB(usernameValue, apartmentValue);
            if (errorCode == 0) messageWindow("User: " + usernameValue + " added to apartment: " + apartmentValue);
            else errorWindow("Error number: " + errorCode);
        });
        return addBtn;
    }

    private JPanel addUser() {
        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new GridLayout(5, 1));

        JLabel addUserLabel = new JLabel("  Add user: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel pinLabel = new JLabel("PIN: ");
        JLabel roleLabel = new JLabel("Role: ");

        JPanel pinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(100, 30));

        JTextField pin = new JTextField();
        pin.setPreferredSize(new Dimension(100, 30));

        String[] choices = {"user", "admin"};
        JComboBox<String> role = new JComboBox<>(choices);

        JButton addBtn = addUserBtn(username, pin, role);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        pinPanel.add(pinLabel);
        pinPanel.add(pin);
        rolePanel.add(roleLabel);
        rolePanel.add(role);

        addUserPanel.add(addUserLabel);
        addUserPanel.add(usernamePanel);
        addUserPanel.add(pinPanel);
        addUserPanel.add(rolePanel);
        addUserPanel.add(addBtn, new FlowLayout(FlowLayout.LEFT));
        return addUserPanel;
    }

    private JButton addUserBtn(JTextField username, JTextField pin, JComboBox role) {
        JButton userBtn = new JButton("ADD");

        userBtn.addActionListener(_ -> {
            String pinValue = pin.getText();
            String usernameValue = username.getText();
            String roleValue = Objects.requireNonNull(role.getSelectedItem()).toString();

            if (usernameValue.length() > 25) {
                errorWindow("Usernames must be less than 25 characters!");
                return;
            }
            if (pinValue.length() > 4) {
                errorWindow("PIN must be 4 digits!");
                return;
            }
            if (!CheckValue.isNumber(pinValue)) {
                errorWindow("PIN must be a number!");
                return;
            }

            errorCode = userDAO.addUserToDB(usernameValue, pinValue, roleValue);
            switch (errorCode) {
                case 0:
                    messageWindow("User: " + usernameValue + " added");
                    break;
                case 1062:
                    errorWindow("User already exists!");
                    break;
                default:
                    System.out.println("Error number: " + errorCode);
                    break;
            }
        });
        return userBtn;
    }

    private JPanel addApartment() {
        JPanel addApartmentPanel = new JPanel(new GridLayout(3, 1));

        JLabel addApartmentLabel = new JLabel("  Add apartment: ");
        JLabel apartmentNrLabel = new JLabel("NR: ");

        JPanel apartmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField nr = new JTextField();
        nr.setPreferredSize(new Dimension(100, 30));

        JButton addBtn = addApartmentBtn(nr);

        apartmentPanel.add(apartmentNrLabel);
        apartmentPanel.add(nr);

        addApartmentPanel.add(addApartmentLabel);
        addApartmentPanel.add(apartmentPanel);
        addApartmentPanel.add(addBtn, new FlowLayout(FlowLayout.LEFT));
        return addApartmentPanel;
    }

    private JButton addApartmentBtn(JTextField nr) {
        JButton addBtn = new JButton("ADD");

        addBtn.addActionListener(_ -> {
            System.out.println("PIN: " + nr.getText());
            String pin = nr.getText();

            if (!CheckValue.isNumber(pin)) {
                errorWindow("Apartment number must be a number!");
                return;
            }

            errorCode = userDAO.addApartmentToDB(pin);

            switch (errorCode) {
                case 0:
                    messageWindow("Apartment " + nr.getText() + " added");
                    break;
                case 1062:
                    errorWindow("Apartment already exists!");
                    break;
                default:
                    System.out.println("Error number: " + errorCode);
                    break;
            }
        });
        return addBtn;
    }


}
