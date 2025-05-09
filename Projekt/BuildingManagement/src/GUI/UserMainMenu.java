package GUI;

import javax.swing.*;

public class UserMainMenu {
    private final static Window window = new Window();

    public static void run() {
        JFrame mainFrame = window.setWindow("User main menu", 800, 600, true);
    }
}
