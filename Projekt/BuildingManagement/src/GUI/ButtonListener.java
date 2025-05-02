package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonListener {

    public void addButtonListener(JButton button, ActionListener listener) {
        button.addActionListener(listener);
    }
}