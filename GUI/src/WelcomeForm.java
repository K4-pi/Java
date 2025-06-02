import javax.swing.*;

public class WelcomeForm extends JFrame {
    private JPanel JPanelMain;
    private JProgressBar progressBar1;
    private JPanel upper;
    private JPanel lower;
    private JLabel wait;

    public WelcomeForm() {
        super("Welcome");
        this.setContentPane(this.JPanelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 400, height = 300;
        this.setSize(width, height);
        this.setVisible(true);

        progression();
    }

    private void progression() {
        int counter = 0;
        while (counter <= 100) {
            wait.setText("Proszę czekać...");
            progressBar1.setValue(counter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 10;
        }
        dispose();
        LoginForm loginForm = new LoginForm();
    }
}
