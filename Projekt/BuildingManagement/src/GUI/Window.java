package GUI;

import javax.swing.*;

public class Window extends JFrame{

    public Window(String title, int sizeX, int sizeY, boolean resizable) {
        this.setTitle(title);
        this.setSize(sizeX, sizeY);
        this.setResizable(resizable);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Window(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose) {
        this.setTitle(title);
        this.setSize(sizeX, sizeY);
        this.setResizable(resizable);
        if (disposeOnClose) this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        else this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void errorWindow(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void messageWindow(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
