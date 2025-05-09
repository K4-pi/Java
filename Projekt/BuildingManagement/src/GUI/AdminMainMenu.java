package GUI;

import javax.swing.*;

public class AdminMainMenu {
    private static final Window window = new Window();

    public static void run() {
        JFrame mainFrame = window.setWindow("Admin main menu", 800, 600, true);
    }
}