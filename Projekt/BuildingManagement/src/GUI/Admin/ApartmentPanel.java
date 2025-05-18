package GUI.Admin;

import Database.UserDAO;
import GUI.CustomComponents;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ApartmentPanel extends Window {
    private final UserDAO userDAO = new UserDAO();
    private final int apartmentNr;
    private JTextArea apartmentLabel;

    public ApartmentPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximize, boolean disposeOnClose, int apartmentNr) {
        super(title, sizeX, sizeY, resizable, maximize, disposeOnClose);
        this.apartmentNr = apartmentNr;
    }

    public void run(JFrame parent) throws SQLException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        apartmentLabel = new JTextArea(6, 10);
        JScrollPane scrollPane = new JScrollPane(apartmentLabel);
        apartmentLabel.setVisible(true);
        apartmentLabel.setEditable(false);
        apartmentLabel.setFocusable(false);
        scrollPane.setVisible(true);
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
        buttonsPanel.add(CustomComponents.switchButtonDouble("Air temp", doubleUpListener("airtemp"), doubleDownListener("airtemp")));
        buttonsPanel.add(CustomComponents.switchButtonDouble("Water temp", doubleUpListener("watertemp"), doubleDownListener("watertemp")));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Electricity", booleanButtonOnListener("electricity", true), booleanButtonOffListener("electricity", false)));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Light", booleanButtonOnListener("light", true), booleanButtonOffListener("light", false)));

        JPanel apartmentPanel = new JPanel(new FlowLayout());
        apartmentPanel.add(scrollPane);

        mainPanel.add(apartmentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        this.add(mainPanel);
    }

    private ActionListener booleanButtonOnListener(String SQLColumnName, boolean value) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, value, apartmentNr);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener booleanButtonOffListener(String SQLColumnName, boolean value) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, value, apartmentNr);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener doubleUpListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, 0.1, apartmentNr);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener doubleDownListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateDoubleValue(SQLColumnName, -0.1, apartmentNr);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
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

