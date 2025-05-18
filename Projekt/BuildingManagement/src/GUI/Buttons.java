package GUI;

import javax.swing.*;

public class Buttons {

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


}
