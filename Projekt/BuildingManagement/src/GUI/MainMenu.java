package GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends Button {

    public void RunMainMenu() {
        Window window = new Window();
        window.setWindow("test", 250, 250, true);

        JButton btn = new JButton("test");
        btn.setSize(new Dimension(50, 50));

        window.addComponent(btn);
    }
}
