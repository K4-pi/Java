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

    public void run() {
        mainFrame = window.setWindow("Building entrance", 400, 500, false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());

        // Who are you logging in as?
        String[] choices = {"user", "admin"};
        chooseUser = new JComboBox<>(choices);
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setVisible(true);
        comboBoxPanel.setLayout(new FlowLayout());
        comboBoxPanel.add(chooseUser);

        // PIN display
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
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        mainFrame.add(mainPanel);
    }

    //On enter button function
    private void onEnter(String pin) throws SQLException {
        if (authenticateUser(chosenUserValue, pin)) {
            if (chosenUserValue.equals("user")) {
                UserPanel.run(chosenUserValue);
            }
            else if (chosenUserValue.equals("admin")) {
                adminPanel.run(chosenUserValue);
            }
            System.out.println("PIN accepted");
            mainFrame.dispose();
        } else {
            pinText.setText("PIN rejected");
        }
    }

    //On click function
    private void addActionListener(JButton b) {
        b.addActionListener(e -> {
            String buttonText = b.getText();
            if (pinText.getText().equals("PIN rejected")) pinText.setText(""); //clear text on button press

            switch (buttonText) {
                case "Enter":
                    chosenUserValue = (String) chooseUser.getSelectedItem();
                    try {
                        onEnter(pinText.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Clear":
                    pinText.setText("");
                    break;
                default:
                    if (pinText.getText().length() < 4) pinText.setText(pinText.getText() + buttonText);
                    break;
            }

        });
    }
}
