package GUI.Admin;

import Database.UserDAO;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ApartmentPanel extends Window {
    private final UserDAO userDAO = new UserDAO();
    private final int apartmentNr;
    private JTextArea apartmentLabel;

    public ApartmentPanel(String title, int sizeX, int sizeY, boolean resizable, boolean disposeOnClose, int apartmentNr) {
        super(title, sizeX, sizeY, resizable, disposeOnClose);
        this.apartmentNr = apartmentNr;
    }

    public void run(JFrame parent) throws SQLException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        apartmentLabel = new JTextArea(4, 10);
        apartmentLabel.setEditable(false);
        apartmentLabel.setFocusable(false);
        showApartmentLabel();

        // On exit set parent window editable
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                parent.setEnabled(true);
            }
        });

        // Buttons that update parameters
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        buttonsPanel.add(switchButtonDouble("airtemp", "Air temp"));
        buttonsPanel.add(switchButtonDouble("watertemp", "Water temp"));
        buttonsPanel.add(switchButtonBoolean("electricity", "Electricity"));
        buttonsPanel.add(switchButtonBoolean("light", "Light"));

        JPanel apartmentPanel = new JPanel(new FlowLayout());
        apartmentPanel.add(apartmentLabel);

        mainPanel.add(apartmentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        this.add(mainPanel);
    }

    private JPanel switchButtonBoolean(String SQLColumnName, String labelText) {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel label = new JLabel(labelText);
        JButton buttonOn = new JButton("ON");
        JButton buttonOff = new JButton("OFF");

        Dimension buttonSize = new Dimension(20, 50);
        buttonOn.setPreferredSize(buttonSize);
        buttonOff.setPreferredSize(buttonSize);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(labelText);
        buttonOn.addActionListener(_ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, true);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonOff.addActionListener(_ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, false);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(label);
        panel.add(buttonOn);
        panel.add(buttonOff);

        return panel;
    }

    private JPanel switchButtonDouble(String SQLColumnName, String labelText) {
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel label = new JLabel(labelText);
        JButton buttonUp = new JButton("UP");
        JButton buttonDown = new JButton("DOWN");

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(labelText);
        buttonUp.addActionListener(_ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, 0.1);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonDown.addActionListener(_ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, -0.1);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(label);
        panel.add(buttonUp);
        panel.add(buttonDown);

        return panel;
    }

    private void showApartmentLabel() throws SQLException {
        ArrayList<Object> apartment = userDAO.viewApartment(apartmentNr);

        apartmentLabel.setText(""); // Clear the text
        for (int i = 0; i < apartment.size(); i++) {
            switch (i) {
                case 0:
                    apartmentLabel.append("NR: " + apartment.get(i) + "\n");
                    break;
                case 1:
                    apartmentLabel.append("Electricity: " + apartment.get(i) + "\n");
                    break;
                case 2:
                    apartmentLabel.append("Light: " + apartment.get(i) + "\n");
                    break;
                case 3:
                    apartmentLabel.append("Air temp: " + new DecimalFormat("##.#").format(apartment.get(i)) + "\n");
                    break;
                case 4:
                    apartmentLabel.append("Water temp: " + new DecimalFormat("##.#").format(apartment.get(i)) + "\n");
                    break;
            }
        }
    }
}

