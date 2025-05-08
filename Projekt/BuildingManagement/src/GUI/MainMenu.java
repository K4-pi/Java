package GUI;

import javax.swing.*;

public class MainMenu {
    private static Window window = new Window();
    private static JFrame mainFrame;

    static void run() {
        mainFrame = window.setWindow("Main Menu", 800, 600, true);
    }
}
