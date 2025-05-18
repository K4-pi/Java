package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomComponents {

    // button that takes you back to main menu
    public static JButton logOutButton() {
        JButton logoutBtn = new JButton("Log out");
        logoutBtn.setVisible(true);

        logoutBtn.addActionListener(_ -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
            new Entry("Building entrance", 400, 500, false, false).run();
            frame.dispose();
        });
        return logoutBtn;
    }

    public static JScrollPane getList(ArrayList<String> arr) {
        JTextArea textArea = new JTextArea(25, 35);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setVisible(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        scrollPane.setVisible(true);

        for (String s : arr) {
            textArea.append(s + "\n");
        }
        return scrollPane;
    }

    public static boolean isNumber(String value) {
        //Checking if String is number
        char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] chars = value.toCharArray();
        boolean isNumber = false;

        for (char c : chars) {
            System.out.println("c: " + c);
            for (char i : numbers) {
                if (c == i) {
                    isNumber = true;
                    break;
                }
                isNumber = false;
            }
        }
        return isNumber;
    }

    public static JPanel switchButtonBoolean(String labelText, ActionListener buttonOnL, ActionListener buttonOffL) {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel label = new JLabel(labelText);
        JButton buttonOn = new JButton("ON");
        JButton buttonOff = new JButton("OFF");

        Dimension buttonSize = new Dimension(20, 50);
        buttonOn.setPreferredSize(buttonSize);
        buttonOff.setPreferredSize(buttonSize);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(labelText);
        buttonOn.addActionListener(buttonOnL);
        buttonOff.addActionListener(buttonOffL);
//        buttonOn.addActionListener(_ -> {
//            try {
//                userDAO.updateBooleanValue(SQLColumnName, true, apartmentNr);
//                showApartmentLabel();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//        buttonOff.addActionListener(_ -> {
//            try {
//                userDAO.updateBooleanValue(SQLColumnName, false, apartmentNr);
//                showApartmentLabel();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

        panel.add(label);
        panel.add(buttonOn);
        panel.add(buttonOff);

        return panel;
    }

    public static JPanel switchButtonDouble(String labelText, ActionListener buttonUpL, ActionListener buttonDownL) {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel label = new JLabel(labelText);
        JButton buttonUp = new JButton("UP");
        JButton buttonDown = new JButton("DOWN");

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(labelText);
        buttonUp.addActionListener(buttonUpL);
        buttonDown.addActionListener(buttonDownL);
//        buttonUp.addActionListener(_ -> {
//            try {
//                userDAO.updateDoubleValue(SQLColumnName, 0.1, apartmentNr);
//                showApartmentLabel();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//        buttonDown.addActionListener(_ -> {
//            try {
//                userDAO.updateDoubleValue(SQLColumnName, -0.1, apartmentNr);
//                showApartmentLabel();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

        panel.add(label);
        panel.add(buttonUp);
        panel.add(buttonDown);

        return panel;
    }
}
