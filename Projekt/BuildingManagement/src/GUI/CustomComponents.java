package GUI;

import javax.swing.*;
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
}
