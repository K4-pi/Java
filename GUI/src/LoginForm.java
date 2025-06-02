import javax.swing.*;

public class LoginForm extends JFrame {
    private JPanel panel1;
    private JTextField LoginUser;
    private JTextField PasswordUser;
    private JButton Login;
    private JButton Exit;
    private JPanel usernamePanel;
    private JLabel Username;
    private JLabel Password;
    private JPanel passwordPanel;
    private JPanel Buttons;
    private JLabel OutputLabel;

    String user = "admin", password = "admin";

    public LoginForm() {
        super("Login");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 400, height = 300;
        this.setSize(width, height);
        this.setVisible(true);

        Exit.addActionListener(e -> {
            dispose();
        });

        Login.addActionListener(e -> {
            String userNameInput = LoginUser.getText();
            String passwordInput = PasswordUser.getText();

            if (userNameInput.equals(user) && passwordInput.equals(password)) {
                OutputLabel.setText("Zalogowano jako: " + user);
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
            else OutputLabel.setText("Złe dane");
            PasswordUser.setText("");
            LoginUser.setText("");
        });
    }
}
