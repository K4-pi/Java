package GUI;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;

    public void setWindow(String title, int sizeX, int sizeY, boolean resizable) {
        frame = new JFrame(title);
        frame.setSize(new Dimension(sizeX, sizeY));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(resizable);
        frame.setVisible(true);
    }

    public void addComponent(Component c) {
        frame.add(c);
    }
}
