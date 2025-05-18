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

        apartmentLabel = new JTextArea(6, 14);
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
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Electricity", booleanButtonOnListener("electricity"), booleanButtonOffListener("electricity")));
        buttonsPanel.add(CustomComponents.switchButtonBoolean("Light", booleanButtonOnListener("light"), booleanButtonOffListener("light")));

        JPanel apartmentPanel = new JPanel(new FlowLayout());
        apartmentPanel.add(scrollPane);

        mainPanel.add(apartmentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        this.add(mainPanel);
    }

    private ActionListener booleanButtonOnListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, true, apartmentNr);
                showApartmentLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };
    }

    private ActionListener booleanButtonOffListener(String SQLColumnName) {
        return _ -> {
            try {
                userDAO.updateBooleanValue(SQLColumnName, false, apartmentNr);
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
        ArrayList<Object> list = userDAO.viewApartment(apartmentNr);

        apartmentLabel.setText(""); // Clear the text
        apartmentLabel.append("Apartment number: " + list.get(0) + "\n");
        apartmentLabel.append("Electricity: " + list.get(1) + "\n");
        apartmentLabel.append("Light: " + list.get(2) + "\n");
        apartmentLabel.append("Air temp: " + list.get(3) + " Celsius\n");
        apartmentLabel.append("Water temp: " + list.get(4) + " Celsius\n");
    }
}

