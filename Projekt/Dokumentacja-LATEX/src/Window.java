public abstract class Window extends JFrame{

    public Window(String title, int sizeX, int sizeY, boolean resizable, boolean maximized) {
        //Almost centers the window in the middle of a screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width  / 4);
        int y = (screenSize.height  / 6);
        this.setLocation(x, y);

        this.setTitle(title);
        this.setSize(sizeX, sizeY);
        this.setResizable(resizable);
        if (maximized) this.setExtendedState(MAXIMIZED_BOTH);// Maximizes the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Window(String title, int sizeX, int sizeY, boolean resizable, boolean maximized, boolean disposeOnClose) {
        //Almost centers the window in the middle of a screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width / 4);
        int y = (screenSize.height / 6);
        this.setLocation(x, y);

        this.setTitle(title);
        this.setSize(sizeX, sizeY);
        this.setResizable(resizable);
        if (maximized) this.setExtendedState(MAXIMIZED_BOTH);// Maximizes the window
        if (disposeOnClose) this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        else this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void errorWindow(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void messageWindow(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
