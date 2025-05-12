package GUI;

import Database.UserDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class Buttons {

    // button that takes you back to main menu
    public static JButton logOutButton() {
        JButton logoutBtn = new JButton("Log out");
        logoutBtn.setVisible(true);

        logoutBtn.addActionListener(_ -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logoutBtn);
            new Entry("Building entrance", 400, 500, false).run();
            frame.dispose();
        });

        return logoutBtn;
    }


}
