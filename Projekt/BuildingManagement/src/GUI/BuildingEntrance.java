package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BuildingEntrance extends UserDAO {
    private final Window window = new Window();
    private final AdminPanel adminPanel = new AdminPanel();

    private JFrame mainFrame;
    private JComboBox<String> chooseUser;
    private Label pinText;
    private String chosenUserValue;
    private JTextField usernameField;
    private String username;

    public void run() {
        mainFrame = window.setWindow("Building entrance", 400, 500, false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Who are you logging in as?
        String[] choices = {"user", "admin"};
        chooseUser = new JComboBox<>(choices);
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setVisible(true);
        comboBoxPanel.setLayout(new FlowLayout());
        comboBoxPanel.add(chooseUser);

        // Enter username
        usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(100, 30));
        usernameField.setToolTipText("Enter username");
        JPanel usernameInsertPanel = new JPanel();
        usernameInsertPanel.setLayout(new FlowLayout());
        usernameInsertPanel.add(usernameField);

        // PIN display
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        pinText = new Label();
        pinText.setText("");
        pinText.setPreferredSize(new Dimension(300, 200));
        pinText.setAlignment(Label.CENTER);
        textPanel.add(pinText);

        // Buttons with panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));

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

        mainPanel.add(comboBoxPanel);
        mainPanel.add(usernameInsertPanel);
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        mainFrame.add(mainPanel);
    }

    //On enter button function
    private void onEnter(String pin) throws SQLException {
        if (authenticateUser(chosenUserValue, pin, username)) {
            if (chosenUserValue.equals("user")) {
                if (!isClosed()) {
                    UserPanel.run(username);
                    mainFrame.dispose();
                    System.out.println("PIN accepted");
                }
                else pinText.setText("Building is closed");
            }
            else if (chosenUserValue.equals("admin")) {
                adminPanel.run(username);
                mainFrame.dispose();
                System.out.println("PIN accepted");
            }
        } else {
            pinText.setText("Wrong PIN or username!");
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
