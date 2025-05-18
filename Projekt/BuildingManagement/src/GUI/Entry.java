package GUI;

import Database.UserDAO;
import GUI.Admin.AdminPanel;
import GUI.User.UserPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Entry extends Window {
    UserDAO userDAO = new UserDAO();

    private JComboBox<String> chooseUser;
//    private Label pinText;
    private JTextField pinText;
    private String chosenUserValue;
    private JTextField usernameField;
    private String username;

    public Entry(String title, int sizeX, int sizeY, boolean resizable) {
        super(title, sizeX, sizeY, resizable);
    }

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Who are you logging in as?
        String[] choices = {"user", "admin"};
        chooseUser = new JComboBox<>(choices);
        JPanel comboBoxPanel = new JPanel(new FlowLayout());
        comboBoxPanel.setVisible(true);
        comboBoxPanel.add(chooseUser);

        // Enter username
        usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(100, 30));
        usernameField.setToolTipText("Enter username");
        usernameField.setBackground(Color.WHITE);
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel usernameInsertPanel = new JPanel(new FlowLayout());
        usernameInsertPanel.add(usernameField);

        // PIN display
        pinText = new JTextField(10);
        pinText.setPreferredSize(new Dimension(100, 30));
        pinText.setToolTipText("Enter PIN");
        pinText.setBackground(Color.WHITE);
        pinText.setEditable(false);
        pinText.setFocusable(false);
        pinText.setText("");
        pinText.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.add(pinText);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 3));

        JButton[] btns = {
            new JButton("7"),
            new JButton("8"),
            new JButton("9"),
            new JButton("4"),
            new JButton("5"),
            new JButton("6"),
            new JButton("1"),
            new JButton("2"),
            new JButton("3"),
            new JButton("Clear"),
            new JButton("0"),
            new JButton("Enter")
        };

        for (JButton b : btns) {
            b.setPreferredSize(new Dimension(50, 50));

            //Adds action listeners to buttons
            addActionListener(b);
            buttonPanel.add(b);
        }

        JPanel usernameLabel = new JPanel();
        usernameLabel.setLayout(new BoxLayout(usernameLabel, BoxLayout.X_AXIS));
        usernameLabel.add(new JLabel("Username"));

        JPanel pinLabel = new JPanel();
        pinLabel.setLayout(new BoxLayout(pinLabel, BoxLayout.X_AXIS));
        pinLabel.add(new JLabel("PIN"));

        mainPanel.add(comboBoxPanel);
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameInsertPanel);
        mainPanel.add(pinLabel);
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
    }

    //On enter button function
    private void onEnter(String pin) throws SQLException {
        if (userDAO.authenticateUser(chosenUserValue, pin, username)) {
            if (chosenUserValue.equals("user")) {
                if (!userDAO.isClosed()) {
                    new UserPanel(username + "'s panel", 800, 600, true).run();
                    this.dispose();
                    System.out.println("PIN accepted");
                }
                else messageWindow("Building is closed");
            }
            else if (chosenUserValue.equals("admin")) {
                new AdminPanel(username + "'s panel", 800, 600, true).run();
                this.dispose();
                System.out.println("PIN accepted");
            }
        } else {
            errorWindow("Wrong PIN or username!");
        }
    }

    //On click function
    private void addActionListener(JButton b) {
        b.addActionListener(_ -> {
            String buttonText = b.getText();
            String getPinText = pinText.getText();
            if (getPinText.length() > 4) pinText.setText(buttonText); //clears text when pressed something while error message

            switch (buttonText) {
                case "Enter":
                    chosenUserValue = (String) chooseUser.getSelectedItem(); // get selected user type (user/admin)
                    username = usernameField.getText(); // get username from text field
                    try {
                        onEnter(getPinText);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Clear":
                    pinText.setText("");
                    break;
                default:
                    if (getPinText.length() < 4) pinText.setText(getPinText + buttonText);
                    break;
            }
        });
    }
}
