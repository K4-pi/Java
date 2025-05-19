package GUI.Admin;

import GUI.Window;

import javax.swing.*;

public class ReportMessageWindow extends Window {

    public ReportMessageWindow(String title, int sizeX, int sizeY, boolean resizable, boolean maximized, boolean disposeOnClose, String message) {
        super(title, sizeX, sizeY, resizable, maximized, disposeOnClose);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea messageArea = new JTextArea(10, 10);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        messageArea.setFocusable(false);
        messageArea.setText(message);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        mainPanel.add(scrollPane);

        this.add(mainPanel);
    }

}
