package GUI;

import javax.swing.*;

public class UserPanel {
    private final static Window window = new Window();

    public static void run() {
        JFrame mainFrame = window.setWindow("User panel", 800, 600, true);
    }
}
