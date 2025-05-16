package GUI.Admin;

import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddApartmentUser extends Window  {

    public AddApartmentUser(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose) {
        super(title, sizeX, sizeY, resizable, disposeOnClose);
    }

    public void run(JFrame parent) {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        //Enable parent window on close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                parent.setEnabled(true);
            }
        });

        mainPanel.add(addUser());

        this.add(mainPanel);
        this.pack();
    }

    private JPanel addUser() {
        JPanel addUserPanel = new JPanel();
        addUserPanel.setLayout(new BoxLayout(addUserPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel pinLabel = new JLabel("PIN: ");

        JPanel pinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextArea username = new JTextArea();
        username.setRows(1);
        username.setColumns(20);

        JTextArea pin = new JTextArea();
        pin.setRows(1);
        pin.setColumns(20);

        JButton addBtn = new JButton("ADD");

        addBtn.addActionListener(_ -> {
            System.out.println("username: " + username.getText());
            System.out.println("PIN: " + pin.getText());
        });

        usernamePanel.add(username);
        pinPanel.add(pin);
        pinPanel.add(addBtn);

        addUserPanel.add(usernameLabel);
        addUserPanel.add(usernamePanel);
        addUserPanel.add(pinLabel);
        addUserPanel.add(pinPanel);
        return addUserPanel;
    }

}
