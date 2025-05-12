package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

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

    // Create new window
    /*public JFrame setWindow(String title, int sizeX, int sizeY, boolean resizable) {
        JFrame frame = new JFrame(title);
        frame.setResizable(resizable);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setVisible(true);

        return frame;
    }*/

    // Panel that displays user info
    /*public JLabel updateUserInfoPanel(String user) throws SQLException {
        UserDAO userDAO = new UserDAO();
        String apartmentInfo = userDAO.apartmentInfo(userDAO.userId(user));

        JLabel userInfoLabel = new JLabel("<html>Hi " + user + "!<br/>" + apartmentInfo +"</html>", SwingConstants.LEFT);
        userInfoLabel.setVisible(true);

        return userInfoLabel;
    }*/



}
