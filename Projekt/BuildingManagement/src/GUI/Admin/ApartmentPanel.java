package GUI.Admin;

import GUI.Window;

import javax.swing.*;

public class ApartmentPanel {

    Window window = new Window("Apartment panel", 400, 400, false, true);

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        window.add(mainPanel);
    }
}

