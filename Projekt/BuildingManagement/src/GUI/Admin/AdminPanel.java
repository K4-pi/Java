package GUI.Admin;

import Database.UserDAO;
import GUI.CustomComponents;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AdminPanel extends Window {
    private final UserDAO userDAO = new UserDAO();

    public AdminPanel(String title, int sizeX, int sizeY, boolean resizable, boolean maximized) {
        super(title, sizeX, sizeY, resizable, maximized);
    }

    public void run() throws SQLException {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Close button
        JPanel btnsPanel = new JPanel(new GridLayout(1, 2));
        btnsPanel.add(closeBuildingButton());

        // Logout button
        btnsPanel.add(CustomComponents.logOutButton());

        // List and reports panel
        JPanel combined = new JPanel(new GridLayout(1, 1));
        combined.add(displayLists());

        // main panel
        mainPanel.add(combined, BorderLayout.CENTER);
        mainPanel.add(btnsPanel, BorderLayout.PAGE_END);

        this.add(mainPanel);
    }

    // List of apartments and field for choosing apartment
    private JPanel displayLists() throws SQLException {
        // Lists
        JScrollPane apartmentsList = CustomComponents.getList(userDAO.getApartments());
        JScrollPane usersList = CustomComponents.getList(userDAO.getUsers());
        JScrollPane reportsList = CustomComponents.getList(userDAO.getReports());

        JPanel apartmentsAndUsersPanel = new JPanel(new GridLayout(2, 1));
        apartmentsAndUsersPanel.add(apartmentsList);
        apartmentsAndUsersPanel.add(usersList);

        // Combined users, apartments and reports
        JPanel combineListsPanel = new JPanel(new GridLayout(1, 2));
        combineListsPanel.add(reportsList);
        combineListsPanel.add(apartmentsAndUsersPanel);

        // Select apartment field and button
        JTextField chooseApartmentField = new JTextField(10);
        JTextField reportField = new JTextField(10);
        chooseApartmentField.setPreferredSize(new Dimension(100, 30));
        reportField.setPreferredSize(new Dimension(100, 30));

        JButton applyBtn = new JButton("Modify");
        applyBtn.addActionListener(_ -> {
            // Get apartment number
            int nr = -1;
            String choosenNumber = chooseApartmentField.getText();
            if (!choosenNumber.isEmpty()) nr = Integer.parseInt(choosenNumber);

            try {
                if (nr > 0) {
                    this.setEnabled(false);
                    new ApartmentPanel("Apartment panel", 400, 400, false, false, true, nr).run(this);
                }
                else System.out.println("Invalid apartment number");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            chooseApartmentField.setText("");
        });

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(_ -> {
            this.dispose();
            new Add("Add", 400, 300, false, false, true).run();
        });

        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(_ -> {
            this.dispose();
            new Remove("Remove", 300, 200, false, false, true).run();
        });

        JButton reportBtn = new JButton("See report");
        reportBtn.addActionListener(_ -> {
            String message;
            try {
                message = userDAO.getReportMessage(Integer.parseInt(reportField.getText()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (message == null) messageWindow("Report not found");
            else new ReportMessageWindow("Report", 400, 300, true, false, true, message);
            reportField.setText("");
        });

        JPanel reportPanel = new JPanel(new FlowLayout());
        reportPanel.add(new JLabel("Report ID"));
        reportPanel.add(reportField);
        reportPanel.add(reportBtn);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addBtn);
        buttonsPanel.add(removeBtn);
        buttonsPanel.add(new JLabel("Apartment nr: "));
        buttonsPanel.add(chooseApartmentField);
        buttonsPanel.add(applyBtn);

        // List and field for choosing apartment
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
        combinedPanel.add(combineListsPanel);
        combinedPanel.add(buttonsPanel);
        combinedPanel.add(reportPanel);

        return combinedPanel;
    }

    // Closing building functionality
    private JButton closeBuildingButton() throws SQLException {
        JButton closeBtn = new JButton();
        if (userDAO.isClosed()) closeBtn.setText("OPEN BUILDING");
        else closeBtn.setText("CLOSE BUILDING");

        closeBtn.addActionListener(_ -> {
            try {
                userDAO.updateDoorStatus(!userDAO.isClosed());
                if (userDAO.isClosed()) closeBtn.setText("OPEN BUILDING");
                else closeBtn.setText("CLOSE BUILDING");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        closeBtn.setVisible(true);

        return closeBtn;
    }
}