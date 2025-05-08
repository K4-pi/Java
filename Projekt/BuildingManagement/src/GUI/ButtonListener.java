package GUI;

import javax.swing.*;

public abstract class ButtonListener {

    public void addActionListener(JButton b) {
        b.addActionListener(e -> {
            System.out.println("Button clicked, but not assigned to any action");
        });
    }

}