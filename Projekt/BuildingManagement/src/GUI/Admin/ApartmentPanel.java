package GUI.Admin;

import GUI.Window;

import javax.swing.*;

public class ApartmentPanel extends Window{

//    Window window = new Window("Apartment panel", 400, 400, false, true);

    public ApartmentPanel(String title, int sizeX, int sizeY, boolean resizable) {
        super(title, sizeX, sizeY, resizable);
    }

    public ApartmentPanel(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose) {
        super(title, sizeX, sizeY, resizable, disposeOnClose);
    }

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.add(mainPanel);
    }
}

