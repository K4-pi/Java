package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BuildingEntrance extends UserDAO {
    private Window window = new Window();
    private JFrame mainFrame;

    private Label pinText;

    public void run() {
        mainFrame = window.setWindow("Building entrance", 400, 500, false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());

        pinText = new Label();
        pinText.setText("");
        pinText.setPreferredSize(new Dimension(300, 200));
        pinText.setAlignment(Label.CENTER);
        textPanel.add(pinText);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3));

        JButton[] btns = new JButton[12];
        btns[0] = new JButton("7");
        btns[1] = new JButton("8");
        btns[2] = new JButton("9");
        btns[3] = new JButton("4");
        btns[4] = new JButton("5");
        btns[5] = new JButton("6");
        btns[6] = new JButton("1");
        btns[7] = new JButton("2");
        btns[8] = new JButton("3");
        btns[9] = new JButton("Clear");
        btns[10] = new JButton("0");
        btns[11] = new JButton("Enter");

        for (JButton b : btns) {
            b.setPreferredSize(new Dimension(50, 50));

            //Adds action listeners to buttons
            addActionListener(b);
            buttonPanel.add(b);
        }

        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        mainFrame.add(mainPanel);
    }

    //On enter button
    private void onEnter(String pin) throws SQLException {
        if (authenticateUser("user", pinText.getText())) {
            System.out.println("PIN accepted");
            mainFrame.dispose();
            MainMenu.run();
        } else {
            pinText.setText("PIN rejected");
        }
    }

    //On click function
    private void addActionListener(JButton b) {
        b.addActionListener(e -> {
            String buttonText = b.getText();

            switch (buttonText) {
                case "Enter":
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
